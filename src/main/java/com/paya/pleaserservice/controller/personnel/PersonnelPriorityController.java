package com.paya.pleaserservice.controller.personnel;

import com.paya.pleaserservice.dto.PersonnelPriorityDto;
import com.paya.pleaserservice.dto.PleaserRequestServiceResponseDto;
import com.paya.pleaserservice.service.personnel.PersonnelFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("personnel/pleaser-services/priorities")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class PersonnelPriorityController {
    private final PersonnelFacadeService service;


    @GetMapping("{id}")
    public PleaserRequestServiceResponseDto getPriority(@PathVariable String id) {
        return service.getPersonnelPriority(id);
    }


    // TODO : Get personnel ID from token


    /**
     * Get pending priority and included priority
     *
     * @return list of PersonnelPriorityDto
     */
    @GetMapping
    public Page<PersonnelPriorityDto> getPriorities(
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false, value = "service_amount_from") Long serviceAmountFrom,
            @RequestParam(required = false, value = "service_count_to") Long serviceAmountTo,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(value = "page_size", required = false, defaultValue = "15") int pageSize
    ) {
        return service.getPersonnelPriorities(
                type,
                title,
                status,
                serviceAmountFrom,
                serviceAmountTo,
                PageRequest.of(page, pageSize)
        );
    }

}
