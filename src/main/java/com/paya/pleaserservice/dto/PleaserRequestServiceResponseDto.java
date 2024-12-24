package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserService;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;

import java.time.LocalDateTime;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PleaserRequestServiceResponseDto extends PleaserServicePriorityDetailDto {
    private String pleaserServiceServiceNumber;
    private LocalDateTime pleaserServiceCreatedAt;
    private ServiceTypeDto pleaserServiceType;
    private int pleaserServiceCount;
    private String pleaserServicePriorityPleaserServiceId;


    public PleaserRequestServiceResponseDto(
            TblPleaserServicePriority priority,
            TblPleaserService service
    ) {
        super(priority, false, service.getPleaserServiceTitle());
        pleaserServiceCount = service.getPleaserServiceCount();
        pleaserServiceServiceNumber = service.getPleaserServiceServiceNumber();
        pleaserServiceCreatedAt = service.getPleaserServiceCreatedAt();
        pleaserServiceType = service.getPleaserServiceType();
        pleaserServicePriorityPleaserServiceId = priority.getPleaserServicePriorityPleaserServiceId();
    }


    public String getPleaserServicePriorityPleaserServiceId() {
        return pleaserServicePriorityPleaserServiceId;
    }


    public String getPleaserServiceServiceNumber() {
        return pleaserServiceServiceNumber;
    }


    public LocalDateTime getPleaserServiceCreatedAt() {
        return pleaserServiceCreatedAt;
    }


    public ServiceTypeDto getPleaserServiceType() {
        return pleaserServiceType;
    }


    public int getPleaserServiceCount() {
        return pleaserServiceCount;
    }

}
