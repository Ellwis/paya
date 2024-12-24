package com.paya.pleaserservice.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PleasureServiceRequestModel {
    private String pleaserServiceRequestId;
    private String pleaserServiceRequestApplicantPersonnelId;
    private String pleaserServiceRequestPleaserServicePriorityId;
    private String pleaserServiceRequestPleaserPriorityPaymentMethodId;
    private String pleaserServiceRequestPaymentRegimeId;
    private int pleaserServiceRequestStatus = 2;
    private int pleaserServiceRequestCount;
    private String pleaserServiceRequestReviewerPersonnelId;
}
