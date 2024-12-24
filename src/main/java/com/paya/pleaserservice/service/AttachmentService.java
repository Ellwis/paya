package com.paya.pleaserservice.service;

import com.paya.pleaserservice.dto.AttachmentDto;
import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.request.AttachmentRequestDto;
import com.paya.pleaserservice.entity.TblAttachment;
import com.paya.pleaserservice.enums.AttachmentType;
import com.paya.pleaserservice.repository.AttachmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AttachmentService {
    private final AttachmentRepository repository;


    public AttachmentDto create(AttachmentRequestDto request) {
        TblAttachment attachment = new TblAttachment();
        attachment.setAttachmentFile(request.getFile());
        attachment.setAttachmentName(request.getName());
        switch (request.getType()) {
            case REGIME -> attachment.setAttachmentPaymentRegimeId(request.getId());
            case PLEASER_SERVICE_PRIORITY -> attachment.setAttachmentPleaserServicePriorityId(request.getId());
            case PLEASER_SERVICE -> attachment.setAttachmentPleaserServiceId(request.getId());
        }

        return new AttachmentDto(save(attachment));
    }


    public List<AttachmentDto> getAll(String id, AttachmentType type) {
        List<TblAttachment> attachments;
        switch (type) {
            case REGIME -> attachments = repository.findAllByAttachmentPaymentRegimeId(id);
            case PLEASER_SERVICE -> attachments = repository.findAllByAttachmentPleaserServiceId(id);
            case PLEASER_SERVICE_PRIORITY -> attachments = repository.findAllByAttachmentPleaserServicePriorityId(id);
            case null, default -> attachments = new ArrayList<>();
        }

        return attachments.stream().map(AttachmentDto::new).toList();
    }


    public TblAttachment get(String id) {
        return findById(id);
    }


    private TblAttachment findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "فایل مورد نظر یافت نشد!"));
    }


    private TblAttachment save(TblAttachment attachment) {
        return repository.save(attachment);
    }


    @Transactional
    public GenericMessageDto delete(String id) {
        repository.deleteById(id);
        return new GenericMessageDto("فایل مورد نظر پاک شد!");
    }

}
