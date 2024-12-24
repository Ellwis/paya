package com.paya.pleaserservice.controller.admin;

import com.paya.pleaserservice.dto.PleaserServiceDetailDto;
import com.paya.pleaserservice.dto.PleaserServiceDto;
import com.paya.pleaserservice.dto.PleaserServiceReviewDto;
import com.paya.pleaserservice.dto.PriorityReviewDetailDto;
import com.paya.pleaserservice.dto.request.PriorityConfirmationRequestDto;
import com.paya.pleaserservice.entity.TblPleaserServicePriorityConfirmation;
import com.paya.pleaserservice.service.PleaserServiceFieldService;
import com.paya.pleaserservice.service.PleaserServicePriorityConfirmationService;
import com.paya.pleaserservice.service.PleaserServiceService;
import com.paya.pleaserservice.service.admin.AdminPleaserServiceService;
import com.paya.pleaserservice.utils.DataConversion;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/pleaser-services")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class AdminPleaserServiceController {
    private final PleaserServiceService pleaserServiceService;
    private final PleaserServicePriorityConfirmationService confirmationService;
    private final PleaserServiceFieldService fieldService;
    private final AdminPleaserServiceService service;


    @GetMapping
    public Page<PleaserServiceDto> getAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false, value = "service_count_from") Long serviceCountFrom,
            @RequestParam(required = false, value = "service_count_to") Long serviceCountTo,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(value = "page_size", required = false, defaultValue = "15") int pageSize
    ) {
        return pleaserServiceService.getServices(
                title,
                number,
                DataConversion.convertStringToLocalDateTime(from),
                DataConversion.convertStringToLocalDateTime(to),
                type,
                serviceCountFrom,
                serviceCountTo,
                PageRequest.of(page, pageSize)
        );
    }


    @GetMapping("{id}")
    public PleaserServiceDetailDto get(@PathVariable String id) {
        return pleaserServiceService.getForAdmin(id, fieldService.getAll(id));
    }


    @PostMapping("/priorities/{id}")
    public TblPleaserServicePriorityConfirmation confirmPriority(
            @PathVariable String id,
            @RequestBody PriorityConfirmationRequestDto confirmation
    ) {
        return confirmationService.create(id, confirmation);
    }


    @GetMapping("{id}/priorities/review")
    public PleaserServiceReviewDto getPrioritiesForReview(
            @PathVariable String id
    ) {
        return service.getPrioritiesForReview(id);
    }


    @GetMapping("priorities/{priority_id}/review/detail")
    public PriorityReviewDetailDto getPriorityDetailForReview(
            @PathVariable("priority_id") String priorityId
    ) {
        return service.getPriorityDetailForReview(priorityId);
    }

}
