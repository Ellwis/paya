package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserService;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExpertPleaserServiceDto {
    private String pleaserServiceId;

    private String pleaserServiceServiceNumber;

    private LocalDateTime pleaserServiceCreatedAt;

    private ServiceTypeDto pleaserServiceType;

    private Boolean pleaserServiceIsDraft = true;

    private String pleaserServiceTitle;

    private int pleaserServiceCount;

    private Boolean isChangeable;


    public ExpertPleaserServiceDto(
            TblPleaserService service,
            Boolean isChangeable
    ) {
        this.pleaserServiceId = service.getPleaserServiceId();
        this.pleaserServiceServiceNumber = service.getPleaserServiceServiceNumber();
        this.pleaserServiceCreatedAt = service.getPleaserServiceCreatedAt();
        this.pleaserServiceType = service.getPleaserServiceType();
        this.pleaserServiceIsDraft = service.getPleaserServiceIsDraft();
        this.pleaserServiceTitle = service.getPleaserServiceTitle();
        this.pleaserServiceCount = service.getPleaserServiceCount();
        this.isChangeable = isChangeable;
    }

}
