package com.paya.pleaserservice.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_pleaser_service_request_confirmation")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPleaserServiceRequestConfirmation {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pleaser_service_request_confirmation_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestConfirmationId;


    @PrePersist
    private void generateId() {
        if (pleaserServiceRequestConfirmationId != null) {
            pleaserServiceRequestConfirmationId = UUID.randomUUID().toString();
        }
    }


    @Column(name = "pleaser_service_request_confirmation_pleaser_service_priority_request_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestConfirmationPleaserServicePriorityRequestId;

    @Column(name = "pleaser_service_request_confirmation_reviewer_personnel_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestConfirmationReviewerPersonnelId;

    @Column(name = "pleaser_service_request_confirmation_description")
    private String pleaserServiceRequestConfirmationDescription;

    @Column(name = "pleaser_service_request_confirmation_reviewer_role")
    private int pleaserServiceRequestConfirmationReviewerRole;

    @Column(name = "pleaser_service_request_confirmation_status", columnDefinition = "TINYINT")
    private int pleaserServiceRequestConfirmationStatus;
}