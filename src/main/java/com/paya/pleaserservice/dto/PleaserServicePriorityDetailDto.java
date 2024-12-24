package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPaymentRegime;
import com.paya.pleaserservice.entity.TblPleaserPriorityPaymentMethod;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PleaserServicePriorityDetailDto {
    private String pleaserServicePriorityId;

    private String pleaserServiceTitle;

    private Integer pleaserServicePriorityNumber;

    private Integer pleaserServicePriorityWeight;

    private BigDecimal pleaserServicePriorityAmount;

    private String pleaserServicePriorityMeasurementUnit;

    private Integer pleaserServicePriorityAllowedCount;

    private LocalDate pleaserServicePriorityStartDate;

    private LocalDate pleaserServicePriorityEndDate;

    private boolean pleaserServicePriorityCancelOption;

    private LocalDateTime pleaserServicePriorityMaximumCancellationDate;

    private String pleaserServicePriorityDescription;

    private List<TblPleaserPriorityPaymentMethod> pleaserPriorityPaymentMethodTypes;

    private Double pleaserPriorityPaymentMethodPrepayment;

    private Integer pleaserPriorityPaymentMethodCountOfMonthInstallments;

    private Boolean isChangeable;

    private List<AttachmentDto> files;

    private List<TblPaymentRegime> paymentRegimes;


    public PleaserServicePriorityDetailDto(
            String pleaserServiceTitle,
            int pleaserServicePriorityNumber,
            int pleaserServicePriorityWeight,
            BigDecimal pleaserServicePriorityAmount,
            int pleaserServicePriorityAllowedCount,
            LocalDate pleaserServicePriorityStartDate,
            LocalDate pleaserServicePriorityEndDate,
            boolean pleaserServicePriorityCancelOption,
            LocalDateTime pleaserServicePriorityMaximumCancellationDate,
            String pleaserServicePriorityDescription,
            String pleaserServicePriorityMeasurementUnit
    ) {
        this.pleaserServiceTitle = pleaserServiceTitle;
        this.pleaserServicePriorityNumber = pleaserServicePriorityNumber;
        this.pleaserServicePriorityWeight = pleaserServicePriorityWeight;
        this.pleaserServicePriorityAmount = pleaserServicePriorityAmount;
        this.pleaserServicePriorityAllowedCount = pleaserServicePriorityAllowedCount;
        this.pleaserServicePriorityStartDate = pleaserServicePriorityStartDate;
        this.pleaserServicePriorityEndDate = pleaserServicePriorityEndDate;
        this.pleaserServicePriorityCancelOption = pleaserServicePriorityCancelOption;
        this.pleaserServicePriorityMaximumCancellationDate = pleaserServicePriorityMaximumCancellationDate;
        this.pleaserServicePriorityDescription = pleaserServicePriorityDescription;
        this.pleaserServicePriorityMeasurementUnit = pleaserServicePriorityMeasurementUnit;

    }


    public PleaserServicePriorityDetailDto(
            TblPleaserServicePriority priority,
            Boolean isChangeable,
            String pleaserServiceTitle
    ) {
        this.pleaserServiceTitle = pleaserServiceTitle;
        this.pleaserServicePriorityId = priority.getPleaserServicePriorityId();
        this.pleaserServicePriorityNumber = priority.getPleaserServicePriorityNumber();
        this.pleaserServicePriorityWeight = priority.getPleaserServicePriorityWeight();
        this.pleaserServicePriorityAmount = priority.getPleaserServicePriorityAmount();
        this.pleaserServicePriorityAllowedCount = priority.getPleaserServicePriorityAllowedCount();
        this.pleaserServicePriorityStartDate = priority.getPleaserServicePriorityStartDate();
        this.pleaserServicePriorityEndDate = priority.getPleaserServicePriorityEndDate();
        this.pleaserServicePriorityCancelOption = priority.getPleaserServicePriorityCancelOption();
        this.pleaserServicePriorityMaximumCancellationDate = priority.getPleaserServicePriorityMaximumCancellationDate();
        this.pleaserServicePriorityDescription = priority.getPleaserServicePriorityDescription();
        this.pleaserServicePriorityMeasurementUnit = priority.getPleaserServicePriorityMeasurementUnit();
        this.isChangeable = isChangeable;
    }

}