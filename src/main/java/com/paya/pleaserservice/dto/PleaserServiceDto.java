package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserService;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PleaserServiceDto {
    private String pleaserServiceId;
    private String pleaserServiceServiceNumber;
    private LocalDateTime pleaserServiceCreatedAt;
    private ServiceTypeDto pleaserServiceType;
    private String pleaserServiceTitle;
    private int pleaserServiceCount;
    private String pleaserServicePriorityMeasurementUnit;
    private List<PriorityDto> priorities;


    public PleaserServiceDto(TblPleaserService pleaserService, List<TblPleaserServicePriority> priorities) {
        pleaserServiceId = pleaserService.getPleaserServiceId();
        pleaserServiceServiceNumber = pleaserService.getPleaserServiceServiceNumber();
        pleaserServiceCreatedAt = pleaserService.getPleaserServiceCreatedAt();
        pleaserServiceType = pleaserService.getPleaserServiceType();
        pleaserServiceTitle = pleaserService.getPleaserServiceTitle();
        pleaserServiceCount = pleaserService.getPleaserServiceCount();
        this.priorities = mapToPriorityDto(priorities);
        if (!priorities.isEmpty())
            pleaserServicePriorityMeasurementUnit = priorities.getFirst().getPleaserServicePriorityMeasurementUnit();
    }


    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private static class PriorityDto {
        private String pleaserServicePriorityId;
        private int pleaserServicePriorityStatus;
        public int pleaserServicePriorityNumber;


        public PriorityDto(TblPleaserServicePriority priority) {
            this.pleaserServicePriorityId = priority.getPleaserServicePriorityId();
            //TODO : Set Status
//            this.pleaserServicePriorityStatus = null;
            this.pleaserServicePriorityNumber = priority.getPleaserServicePriorityNumber();
        }

    }


    private List<PriorityDto> mapToPriorityDto(List<TblPleaserServicePriority> priorities) {
        return priorities
                .stream()
                .map(PriorityDto::new)
                .toList();
    }

}
