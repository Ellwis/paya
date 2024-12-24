package com.paya.pleaserservice.controller.expert;

import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.PleaserServiceFieldDto;
import com.paya.pleaserservice.dto.request.PleaserServiceFieldRequestDto;
import com.paya.pleaserservice.entity.TblPleaserServiceField;
import com.paya.pleaserservice.entity.TblPleaserServiceFieldContent;
import com.paya.pleaserservice.service.PleaserServiceFieldService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pleaser-services")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class PleaserServiceFieldController {
    private final PleaserServiceFieldService service;


    @PostMapping("/{pleaser_service_id}/fields")
    public TblPleaserServiceField create(
            @PathVariable("pleaser_service_id") String pleaserServiceId,
            @RequestBody PleaserServiceFieldRequestDto field
    ) {
        return service.create(pleaserServiceId, field);
    }

    @PatchMapping("/fields/{id}")
    public TblPleaserServiceField update(
            @PathVariable String id,
            @RequestBody PleaserServiceFieldRequestDto field
    ) {
        return service.update(id, field);
    }


    @GetMapping("/{pleaser_service_id}/fields")
    public List<PleaserServiceFieldDto> getAll(
            @PathVariable("pleaser_service_id") String pleaserServiceId
    ) {
        return service.getAll(pleaserServiceId);
    }


    @DeleteMapping("fields/{id}")
    public GenericMessageDto delete(@PathVariable String id) {
        return service.delete(id);
    }

    // Contents


    @PostMapping("fields/{id}/contents")
    public TblPleaserServiceFieldContent createContent(
            @PathVariable String id,
            @RequestBody PleaserServiceFieldRequestDto fieldContent
    ) {
        return service.createContent(id, fieldContent);
    }

    @PatchMapping("/fields/contents/{id}")
    public TblPleaserServiceFieldContent updateContent(
            @PathVariable String id,
            @RequestBody PleaserServiceFieldRequestDto content
    ) {
        return service.updateContent(id, content);
    }


    @DeleteMapping("fields/contents/{id}")
    public GenericMessageDto deleteContent(@PathVariable String id) {
        return service.deleteContent(id);
    }

}
