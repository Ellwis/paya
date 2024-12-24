package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExpertPleaserServicePriorityDto {
    private String pleaserServicePriorityId;

    private int pleaserServicePriorityNumber;

    private String pleaserServicePriorityPleaserServiceId;

    private int pleaserServicePriorityWeight;

    private BigDecimal pleaserServicePriorityAmount;

    private int pleaserServicePriorityAllowedCount;

    private String pleaserServicePriorityMeasurementUnit;

    private LocalDate pleaserServicePriorityStartDate;

    private LocalDate pleaserServicePriorityEndDate;

    private String pleaserServicePriorityDescription;

    private BigDecimal pleaserServicePriorityPrepayment;

    private Integer pleaserServicePriorityCountOfMonthInstallments;

    private boolean pleaserServicePriorityIsDraft = true;

    private boolean pleaserServicePriorityCancelOption = false;

    private LocalDateTime pleaserServicePriorityMaximumCancellationDate;

    private Boolean isChangeable;


    public ExpertPleaserServicePriorityDto(TblPleaserServicePriority priority, Boolean isChangeable) {
        this.pleaserServicePriorityId = priority.getPleaserServicePriorityId();
        this.pleaserServicePriorityNumber = priority.getPleaserServicePriorityNumber();
        this.pleaserServicePriorityPleaserServiceId = priority.getPleaserServicePriorityPleaserServiceId();
        this.pleaserServicePriorityWeight = priority.getPleaserServicePriorityWeight();
        this.pleaserServicePriorityAmount = priority.getPleaserServicePriorityAmount();
        this.pleaserServicePriorityAllowedCount = priority.getPleaserServicePriorityAllowedCount();
        this.pleaserServicePriorityMeasurementUnit = priority.getPleaserServicePriorityMeasurementUnit();
        this.pleaserServicePriorityStartDate = priority.getPleaserServicePriorityStartDate();
        this.pleaserServicePriorityEndDate = priority.getPleaserServicePriorityEndDate();
        this.pleaserServicePriorityDescription = priority.getPleaserServicePriorityDescription();
        this.pleaserServicePriorityPrepayment = priority.getPleaserServicePriorityPrepayment();
        this.pleaserServicePriorityCountOfMonthInstallments = priority.getPleaserServicePriorityCountOfMonthInstallments();
        this.pleaserServicePriorityIsDraft = priority.isPleaserServicePriorityIsDraft();
        this.pleaserServicePriorityCancelOption = priority.getPleaserServicePriorityCancelOption();
        this.pleaserServicePriorityMaximumCancellationDate = priority.getPleaserServicePriorityMaximumCancellationDate();
        this.isChangeable = isChangeable;
    }

}
