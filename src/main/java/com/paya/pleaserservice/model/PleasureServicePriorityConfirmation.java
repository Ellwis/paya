package com.paya.pleaserservice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class PleasureServicePriorityConfirmation {
    private String pleaserServicePriorityConfirmationId;
    private String pleaserServicePriorityConfirmationReviewerPersonnelId;
    private int pleaserServicePriorityConfirmationConfirm;
    private int pleaserServicePriorityConfirmationConfirmationLevel;
    private String pleaserServicePriorityConfirmationDescription;
    private String pleaserServicePriorityConfirmationPleaserServicePriorityId;

}
