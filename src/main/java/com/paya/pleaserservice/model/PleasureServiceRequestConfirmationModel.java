package com.paya.pleaserservice.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PleasureServiceRequestConfirmationModel {
    private String pleaserServiceRequestConfirmationId;
    private String pleaserServiceRequestConfirmationPleaserServicePriorityRequestId;
    private String pleaserServiceRequestConfirmationReviewerPersonnelId;
    private String pleaserServiceRequestConfirmationDescription;
    private int pleaserServiceRequestConfirmationReviewerRole;
    private int pleaserServiceRequestConfirmationStatus;

}
