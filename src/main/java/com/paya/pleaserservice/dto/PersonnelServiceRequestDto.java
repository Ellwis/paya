package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserService;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PersonnelServiceRequestDto {
    private String pleaserServicePriorityId;
    private ServiceTypeDto pleaserServiceType;
    private String pleaserServiceTitle;
    private Integer pleaserServicePriorityWeight;
    private BigDecimal pleaserServicePriorityAmount;
    private Integer pleaserServiceRequestStatus;


    public PersonnelServiceRequestDto(
            TblPleaserService pleaserService,
            TblPleaserServicePriority priority,
            Integer pleaserServiceRequestStatus
    ) {
        this.pleaserServicePriorityId = priority.getPleaserServicePriorityId();
        this.pleaserServiceType = pleaserService.getPleaserServiceType();
        this.pleaserServiceTitle = pleaserService.getPleaserServiceTitle();
        this.pleaserServicePriorityWeight = priority.getPleaserServicePriorityWeight();
        this.pleaserServicePriorityAmount = priority.getPleaserServicePriorityAmount();
        this.pleaserServiceRequestStatus = pleaserServiceRequestStatus;
    }

}
