package com.paya.pleaserservice.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PleaserServicePriorityRequestDto {
    private Integer pleaserServicePriorityNumber;

    private Integer pleaserServicePriorityWeight;

    private BigDecimal pleaserServicePriorityAmount;

    private Integer pleaserServicePriorityAllowedCount;

    private String pleaserServicePriorityMeasurementUnit;

    private LocalDate pleaserServicePriorityStartDate;

    private LocalDate pleaserServicePriorityEndDate;

    private String pleaserServicePriorityDescription;

    private Boolean pleaserServicePriorityCancelOption;

    private LocalDateTime pleaserServicePriorityMaximumCancellationDate;


    public TblPleaserServicePriority toPriority(String serviceId) {
        checkNullStatus();
        TblPleaserServicePriority priority = new TblPleaserServicePriority();
        priority.setPleaserServicePriorityNumber(pleaserServicePriorityNumber);
        priority.setPleaserServicePriorityPleaserServiceId(serviceId);
        priority.setPleaserServicePriorityWeight(pleaserServicePriorityWeight);
        priority.setPleaserServicePriorityAmount(pleaserServicePriorityAmount);
        priority.setPleaserServicePriorityAllowedCount(pleaserServicePriorityAllowedCount);
        priority.setPleaserServicePriorityMeasurementUnit(pleaserServicePriorityMeasurementUnit);
        priority.setPleaserServicePriorityStartDate(pleaserServicePriorityStartDate);
        priority.setPleaserServicePriorityEndDate(pleaserServicePriorityEndDate);
        priority.setPleaserServicePriorityDescription(pleaserServicePriorityDescription);
        priority.setPleaserServicePriorityCancelOption(pleaserServicePriorityCancelOption != null ? pleaserServicePriorityCancelOption : false);
        priority.setPleaserServicePriorityMaximumCancellationDate(pleaserServicePriorityMaximumCancellationDate);
        return priority;
    }


    private void checkNullStatus() {
        if (pleaserServicePriorityNumber == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "فیلد شماره اولویت خالی است!");
        if (pleaserServicePriorityWeight == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "فیلد وزن خالی است!");
        if (pleaserServicePriorityAmount == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "فیلد مقدار خالی است!");
        if (pleaserServicePriorityAllowedCount == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "فیلد تعداد مجاز خالی است!");
        if (pleaserServicePriorityMeasurementUnit == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "فیلد مقدار اندازه گیری خالی است!");
        if (pleaserServicePriorityStartDate == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "فیلد تاریخ شروع خالی است!");
        if (pleaserServicePriorityEndDate == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "فیلد تاریخ پایان خالی است!");

    }

}
