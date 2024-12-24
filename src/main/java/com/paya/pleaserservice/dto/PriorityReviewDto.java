package com.paya.pleaserservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriorityReviewDto {
    private String pleaserServicePriorityId;
    private int pleaserServicePriorityWeight;
    private BigDecimal pleaserServicePriorityAmount;
    private int pleaserServicePriorityNumber;
    private Integer status;
    private Integer includedPersonnelNumber;
    private Integer requestedPersonnelNumber;
    private Integer acceptedPersonnelNumber;


    public PriorityReviewDto(
            String pleaserServicePriorityId,
            int pleaserServicePriorityWeight,
            BigDecimal pleaserServicePriorityAmount,
            int pleaserServicePriorityNumber,
            Integer status
    ) {
        this.pleaserServicePriorityId = pleaserServicePriorityId;
        this.pleaserServicePriorityWeight = pleaserServicePriorityWeight;
        this.pleaserServicePriorityAmount = pleaserServicePriorityAmount;
        this.pleaserServicePriorityNumber = pleaserServicePriorityNumber;
        this.status = status;
        includedPersonnelNumber = 10;
        requestedPersonnelNumber = 5;
        acceptedPersonnelNumber = 3;
    }


}
