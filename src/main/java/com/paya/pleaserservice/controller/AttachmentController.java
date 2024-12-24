package com.paya.pleaserservice.controller;

import com.paya.pleaserservice.dto.AttachmentDto;
import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.request.AttachmentRequestDto;
import com.paya.pleaserservice.entity.TblAttachment;
import com.paya.pleaserservice.enums.AttachmentType;
import com.paya.pleaserservice.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("attachments")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class AttachmentController {
    private final AttachmentService service;


    @PostMapping
    public AttachmentDto create(
            @RequestParam String name,
            @RequestParam String file,
            @RequestParam String id,
            @RequestParam AttachmentType type
    ) {
        return service.create(new AttachmentRequestDto(file, id, type, name));
    }


    @DeleteMapping("{id}")
    public GenericMessageDto delete(@PathVariable String id) {
        return service.delete(id);
    }


    @GetMapping
    public List<AttachmentDto> getAll(
            @RequestParam String id,
            @RequestParam AttachmentType type
    ) {
        return service.getAll(id, type);
    }


    @GetMapping("{id}")
    public TblAttachment get(
            @PathVariable String id
    ) {
        return service.get(id);
    }

}
