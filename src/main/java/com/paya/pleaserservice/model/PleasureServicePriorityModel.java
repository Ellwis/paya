package com.paya.pleaserservice.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PleasureServicePriorityModel {
    private String pleaserServicePriorityId;
    private int pleaserServicePriorityNumber;
    private String pleaserServicePriorityPleaserServiceId;
    private int pleaserServicePriorityWeight;
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

}
