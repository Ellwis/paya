package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tbl_attachment")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblAttachment implements Serializable {
    @Id
    @Column(name = "attachment_id")
    private String attachmentId;

    @PrePersist
    public void generateId() {
        if (attachmentId == null) {
            attachmentId = UUID.randomUUID().toString();
        }
    }

    @Column(name = "attachment_file")
    private String attachmentFile;

    @Column(name = "attachment_name")
    private String attachmentName;

    @Column(name = "attachment_payment_regime_id")
    private String attachmentPaymentRegimeId;

    @Column(name = "attachment_pleaser_service_priority_id")
    private String attachmentPleaserServicePriorityId;

    @Column(name = "attachment_pleaser_service_id")
    private String attachmentPleaserServiceId;


}
