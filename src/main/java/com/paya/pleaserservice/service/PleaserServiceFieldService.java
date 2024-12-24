package com.paya.pleaserservice.service;

import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.PleaserServiceFieldDto;
import com.paya.pleaserservice.dto.request.PleaserServiceFieldRequestDto;
import com.paya.pleaserservice.entity.TblPleaserServiceField;
import com.paya.pleaserservice.entity.TblPleaserServiceFieldContent;
import com.paya.pleaserservice.repository.PleaserServiceFieldRepository;
import com.paya.pleaserservice.service.priority.PleaserServicePriorityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PleaserServiceFieldService {
    private final PleaserServiceFieldRepository repository;
    private final PleaserServiceFieldContentService contentService;
    private final PleaserServiceService pleaserServiceService;
    private final PleaserServicePriorityService priorityService;


    public TblPleaserServiceField create(String pleaserServiceId, PleaserServiceFieldRequestDto request) {
        checkTitle(pleaserServiceId, request.getTitle());
        TblPleaserServiceField field = new TblPleaserServiceField();
        field.setPleaserServiceFieldPleaserServiceId(pleaserServiceId);
        field.setPleaserServiceFieldTitle(request.getTitle());

        return save(field);
    }


    public List<PleaserServiceFieldDto> getAll(String pleaserServiceId) {
        return repository.findAllByPleaserServiceId(pleaserServiceId)
                .stream()
                .map(filed -> new PleaserServiceFieldDto(filed, getAllContents(filed.getPleaserServiceFieldId())))
                .toList();
    }


    @Transactional
    public TblPleaserServiceField update(String id, PleaserServiceFieldRequestDto request) {
        TblPleaserServiceField field = findById(id);

        if (request.getTitle() != null)
            field.setPleaserServiceFieldTitle(request.getTitle());

        return field;
    }


    private void checkTitle(String pleaserServiceId, String title) {
        if (repository.existsByServiceIdAndTitle(pleaserServiceId, title.replace(" ", "")))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "این فیلد قبلا ساخته شده است!");
    }


    @Transactional
    public GenericMessageDto delete(String id) {
        checkUpdatable(id);
        deleteAllContents(id);
        repository.deleteById(id);
        return new GenericMessageDto("فیلد مورد نظر حذف شد!");
    }


    @Transactional
    public void deleteAllContents(String fieldId) {
        contentService.deleteAll(fieldId);
    }


    public TblPleaserServiceFieldContent createContent(String fieldId, PleaserServiceFieldRequestDto request) {
        return contentService.create(fieldId, request);
    }


    public List<TblPleaserServiceFieldContent> getAllContents(String fieldId) {
        return contentService.getAll(fieldId);
    }


    @Transactional
    public GenericMessageDto deleteContent(String id) {
        TblPleaserServiceFieldContent content = findContentById(id);
        checkUpdatable(content.getPleaserServiceFieldContentPleaserServiceFieldId());
        contentService.delete(id);
        return new GenericMessageDto("محتوای فیلد با موفقیت حذف شد!");
    }


    @Transactional
    public TblPleaserServiceFieldContent updateContent(String id, PleaserServiceFieldRequestDto request) {
        TblPleaserServiceFieldContent content = findContentById(id);

        if (request.getTitle() != null)
            content.setPleaserServiceFieldContentTitle(request.getTitle());

        return content;
    }


    private TblPleaserServiceFieldContent findContentById(String contentId) {
        return contentService.findById(contentId);
    }


    private void checkUpdatable(String id) {
        TblPleaserServiceField filed = findById(id);
        pleaserServiceService.checkUpdatable(filed.getPleaserServiceFieldPleaserServiceId());
    }


    private TblPleaserServiceField findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "فیلد خدمت یافت نشد!"));
    }


    private TblPleaserServiceField save(TblPleaserServiceField field) {
        return repository.save(field);
    }


    public void checkExistsFieldAndContentsInPriority(String priorityId, List<String> contentIds) {
        List<TblPleaserServiceFieldContent> contents = contentIds.stream().map(this::findContentById).toList();

        String serviceId = priorityService.findById(priorityId).getPleaserServicePriorityPleaserServiceId();

        List<String> fieldIds = getAll(serviceId).stream().map(PleaserServiceFieldDto::getPleaserServiceFieldId).toList();

        contents.forEach(content -> {
            if (!fieldIds.contains(content.getPleaserServiceFieldContentPleaserServiceFieldId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "محتوا برای فیلد های این سرویس تعریف نشده!");
        });


    }

}
