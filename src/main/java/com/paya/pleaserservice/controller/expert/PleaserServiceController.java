package com.paya.pleaserservice.controller.expert;


import com.paya.pleaserservice.dto.ExpertPleaserServiceDto;
import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.PleaserServiceDto;
import com.paya.pleaserservice.dto.ServiceTypeDto;
import com.paya.pleaserservice.dto.request.PleaserServiceRequestDto;
import com.paya.pleaserservice.service.PleaserServiceService;
import com.paya.pleaserservice.utils.DataConversion;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("pleaser-services")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
//@PreAuthorize("hasRole('EXPERT')")
public class PleaserServiceController {
    private final PleaserServiceService service;


    @PostMapping
    public ExpertPleaserServiceDto create(
            @RequestBody PleaserServiceRequestDto pleaserService
    ) {
        return service.create(pleaserService);
    }


    @PatchMapping("{id}")
    public ExpertPleaserServiceDto update(
            @PathVariable String id,
            @RequestBody PleaserServiceRequestDto pleaserService
    ) {
        return service.update(id, pleaserService);
    }


    @GetMapping("{id}")
    public ExpertPleaserServiceDto get(@PathVariable String id) {
        return service.get(id);
    }


    @DeleteMapping("{id}")
    public GenericMessageDto delete(@PathVariable String id) {
        return service.delete(id);
    }


    @GetMapping
    public Page<PleaserServiceDto> getServices(
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
        return service.getServices(
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


    @GetMapping("current")
    public Page<PleaserServiceDto> getCurrentServices(
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
        return service.getCurrentServices(
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


    @GetMapping("types")
    public List<ServiceTypeDto> getServiceTypes() {
        return service.getServiceTypes();
    }

}