package com.paya.pleaserservice.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
// TODO : Refactor this

@Data
@Entity
@Table(name = "tbl_pleaser_service_priority_confirmation")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPleaserServicePriorityConfirmation {
    @Id
    @Column(name = "pleaser_service_priority_confirmation_id", columnDefinition = "char(36)")
    private String pleaserServicePriorityConfirmationId;


    @PrePersist
    private void generateId() {
        if (pleaserServicePriorityConfirmationId == null) {
            pleaserServicePriorityConfirmationId = UUID.randomUUID().toString();
        }
    }


    @Column(name = "pleaser_service_priority_confirmation_reviewer_personnel_id", columnDefinition = "char(36)")
    private String pleaserServicePriorityConfirmationReviewerPersonnelId;

    @Column(name = "pleaser_service_priority_confirmation_confirm", columnDefinition = "bit")
    private int pleaserServicePriorityConfirmationConfirm;

    @Column(name = "pleaser_service_priority_confirmation_confirmation_level", columnDefinition = "tinyint")
    private int pleaserServicePriorityConfirmationConfirmationLevel;

    @Column(name = "pleaser_service_priority_confirmation_description")
    private String pleaserServicePriorityConfirmationDescription;

    @Column(name = "pleaser_service_priority_confirmation_pleaser_service_priority_id", columnDefinition = "char(36)")
    private String pleaserServicePriorityConfirmationPleaserServicePriorityId;

}