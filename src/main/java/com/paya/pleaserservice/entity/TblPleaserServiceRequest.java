package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_pleaser_service_request")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPleaserServiceRequest {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pleaser_service_request_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestId;


    @PrePersist
    private void generateId() {
        if (pleaserServiceRequestId == null) {
            pleaserServiceRequestId = UUID.randomUUID().toString();
        }
    }


    @Column(name = "pleaser_service_request_applicant_personnel_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestApplicantPersonnelId;

    @Column(name = "pleaser_service_request_pleaser_service_priority_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestPleaserServicePriorityId;

    @Column(name = "pleaser_service_request_pleaser_priority_payment_method_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestPleaserPriorityPaymentMethodId;

    @Column(name = "pleaser_service_request_payment_regime_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestPaymentRegimeId;

    @Column(name = "pleaser_service_request_status", columnDefinition = "TINYINT")
    private int pleaserServiceRequestStatus = 2;

    @Column(name = "pleaser_service_request_count")
    private int pleaserServiceRequestCount;

    @Column(name = "pleaser_service_request_reviewer_personnel_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestReviewerPersonnelId;

}
