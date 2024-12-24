package com.paya.pleaserservice.controller.expert;

import com.paya.pleaserservice.dto.ExpertPleaserServicePriorityDto;
import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.PleaserServicePriorityDetailDto;
import com.paya.pleaserservice.dto.request.PleaserServicePriorityRequestDto;
import com.paya.pleaserservice.service.priority.PleaserServicePriorityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pleaser-services")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class PleaserServicePriorityController {
    private final PleaserServicePriorityService service;


    // TODO : get personnel ID from token
    @PostMapping("{service_id}/priorities")
    public ExpertPleaserServicePriorityDto create(
            @PathVariable("service_id") String serviceId,
            @RequestBody PleaserServicePriorityRequestDto priority
    ) {
        return service.create(serviceId, priority);
    }

    @PatchMapping("priorities/{id}")
    public ExpertPleaserServicePriorityDto update(
            @PathVariable String id,
            @RequestBody PleaserServicePriorityRequestDto priority
    ) {
        return service.update(id, priority);
    }

    @GetMapping("{service_id}/priorities")
    public List<PleaserServicePriorityDetailDto> getAll(@PathVariable("service_id") String serviceId) {
        return service.getAll(serviceId);
    }

    @DeleteMapping("priorities/{id}")
    public List<PleaserServicePriorityDetailDto> delete(@PathVariable String id) {
        return service.delete(id);
    }


    @GetMapping("priorities/{priority_id}")
    public PleaserServicePriorityDetailDto getPriority(
            @PathVariable("priority_id") String priorityId
    ) {
        return service.getPriority(priorityId);
    }



    @PatchMapping("priorities/{id}/submit")
    public GenericMessageDto submit(@PathVariable String id) {
        return service.submit(id);
    }
}