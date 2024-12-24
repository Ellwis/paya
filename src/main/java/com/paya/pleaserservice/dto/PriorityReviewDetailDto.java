package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserService;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PriorityReviewDetailDto {
    private String pleaserServiceId;
    private ServiceTypeDto pleaserServiceType;
    private String pleaserServiceTitle;
    private int pleaserServiceCount;
    private String pleaserServicePriorityId;
    private int pleaserServicePriorityWeight;
    private BigDecimal pleaserServicePriorityAmount;
    private int pleaserServicePriorityNumber;
    private Integer pleaserServicePriorityAllowedCount;
    private LocalDate pleaserServicePriorityStartDate;
    private LocalDate pleaserServicePriorityEndDate;
    private LocalDateTime pleaserServicePriorityMaximumCancellationDate;
    private String pleaserServicePriorityDescription;
    private Integer includedPersonnelNumber;
    private Integer requestedPersonnelNumber;
    private Integer acceptedPersonnelNumber;


    public PriorityReviewDetailDto(TblPleaserServicePriority priority, TblPleaserService service) {
        pleaserServiceId = service.getPleaserServiceId();
        pleaserServiceType = service.getPleaserServiceType();
        pleaserServiceTitle = service.getPleaserServiceTitle();
        pleaserServiceCount = service.getPleaserServiceCount();
        pleaserServicePriorityId = priority.getPleaserServicePriorityId();
        pleaserServicePriorityWeight = priority.getPleaserServicePriorityWeight();
        pleaserServicePriorityAmount = priority.getPleaserServicePriorityAmount();
        pleaserServicePriorityNumber = priority.getPleaserServicePriorityNumber();
        pleaserServicePriorityAllowedCount = priority.getPleaserServicePriorityAllowedCount();
        pleaserServicePriorityStartDate = priority.getPleaserServicePriorityStartDate();
        pleaserServicePriorityEndDate = priority.getPleaserServicePriorityEndDate();
        pleaserServicePriorityMaximumCancellationDate = priority.getPleaserServicePriorityMaximumCancellationDate();
        pleaserServicePriorityDescription = priority.getPleaserServicePriorityDescription();
        includedPersonnelNumber = 10;
        acceptedPersonnelNumber = 5;
        requestedPersonnelNumber = 3;

    }

}
