package com.paya.pleaserservice.controller.admin;

import com.paya.pleaserservice.entity.TblPersonnel;
import com.paya.pleaserservice.service.admin.AdminPleaserServiceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/pleaser-services/priorities")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class AdminPleaserServiceRequestController {
    private final AdminPleaserServiceRequestService service;


    @GetMapping("{priority_id}/requests")
    public List<TblPersonnel> getRequests(@PathVariable("priority_id") String priorityId) {
        return service.getRequests(priorityId);
    }


//    @PostMapping
//    public TblPleaserServiceRequestConfirmation confirm(
//            @RequestBody TblPleaserServiceRequestConfirmation requestConfirmation
//    ) {
//        return null;
//    }

}
