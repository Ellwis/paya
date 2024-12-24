package com.paya.pleaserservice.service;

import com.paya.pleaserservice.dto.request.PleaserServiceFieldRequestDto;
import com.paya.pleaserservice.entity.TblPleaserServiceFieldContent;
import com.paya.pleaserservice.repository.PleaserServiceFieldContentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
class PleaserServiceFieldContentService {
    private final PleaserServiceFieldContentRepository repository;


    protected TblPleaserServiceFieldContent create(String fieldId, PleaserServiceFieldRequestDto request) {
        checkTitle(fieldId, request.getTitle());
        TblPleaserServiceFieldContent content = new TblPleaserServiceFieldContent();
        content.setPleaserServiceFieldContentTitle(request.getTitle());
        content.setPleaserServiceFieldContentPleaserServiceFieldId(fieldId);

        return save(content);
    }


    private void checkTitle(String fieldId, String title) {
        if (repository.existsByFieldIdAndTitle(fieldId, title))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "این محتوا قبلا ساخته شده است!");
    }


    protected TblPleaserServiceFieldContent save(TblPleaserServiceFieldContent content) {
        return repository.save(content);
    }


    public List<TblPleaserServiceFieldContent> getAll(String fieldId) {
        return repository.findAllByPleaserServiceFieldContentPleaserServiceFieldId(fieldId);
    }


    public void deleteAll(String fieldId) {
        repository.deleteByPleaserServiceFieldContentPleaserServiceFieldId(fieldId);
    }


    public TblPleaserServiceFieldContent findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "محتوا فیلد مورد نظر یافت نشد!"));
    }


    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

}
