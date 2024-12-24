package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserService;
import lombok.Data;

import java.util.List;


@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PleaserServiceReviewDto {
    private String pleaserServiceId;

    private ServiceTypeDto pleaserServiceType;

    private String pleaserServiceTitle;

    private int pleaserServiceCount;

    private List<PriorityReviewDto> priorities;

    private Integer totalNumberOfIncludedPersonnel = 0;

    private Integer totalNumberOfRequestedPersonnel = 0;

    private Integer totalNumberOfAcceptedPersonnel = 0;


    public PleaserServiceReviewDto(TblPleaserService service, List<PriorityReviewDto> priorities) {
        pleaserServiceId = service.getPleaserServiceId();
        pleaserServiceType = service.getPleaserServiceType();
        pleaserServiceTitle = service.getPleaserServiceTitle();
        pleaserServiceCount = service.getPleaserServiceCount();
        this.priorities = priorities;
        for (PriorityReviewDto priorityReviewDto : priorities) {
            totalNumberOfAcceptedPersonnel += priorityReviewDto.getAcceptedPersonnelNumber();
            totalNumberOfRequestedPersonnel += priorityReviewDto.getRequestedPersonnelNumber();
            totalNumberOfIncludedPersonnel += priorityReviewDto.getIncludedPersonnelNumber();
        }
    }

}