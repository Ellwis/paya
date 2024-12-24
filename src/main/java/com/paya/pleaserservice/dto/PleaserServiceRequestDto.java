package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PleaserServiceRequestDto {
    private String pleaserServiceRequestId;
    private String pleaserServiceRequestApplicantPersonnelId;
    private String pleaserServiceRequestPleaserServicePriorityId;
    private String pleaserServiceRequestPleaserPriorityPaymentMethodId;
    private String pleaserServiceRequestPaymentRegimeId;
    private int pleaserServiceRequestStatus;
}
