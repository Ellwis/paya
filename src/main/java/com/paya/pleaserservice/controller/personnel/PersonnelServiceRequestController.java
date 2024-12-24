package com.paya.pleaserservice.controller.personnel;

import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.request.PleaserServiceRequestRequestDto;
import com.paya.pleaserservice.service.request.PleaserServiceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("personnel/pleaser-services/priorities")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class PersonnelServiceRequestController {
    private final PleaserServiceRequestService service;


    // TODO : Get personnel id from token
    @PostMapping("{priority_id}/requests")
    public GenericMessageDto request(
            @PathVariable("priority_id") String priorityId,
            @RequestBody PleaserServiceRequestRequestDto request
    ) {
        return service.request(priorityId, request);
    }

}
