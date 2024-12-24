package com.paya.pleaserservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PersonnelPriorityDto {
    private String pleaserServicePriorityId;
    private ServiceTypeDto typeDto;
    private String pleaserServiceTitle;
    private String pleaserServicePriorityPleaserServiceId;
    private int pleaserServicePriorityWeight;
    private int status;
    private BigDecimal pleaserServicePriorityAmount;
}
