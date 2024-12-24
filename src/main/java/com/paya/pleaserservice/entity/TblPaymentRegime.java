package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_payment_regime")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPaymentRegime {
    @Id
    @Column(name = "payment_regime_id", columnDefinition = "char(36)")
    private String paymentRegimeId;


    @PrePersist
    public void generateId() {
        if (paymentRegimeId == null) {
            paymentRegimeId = UUID.randomUUID().toString();
        }
    }


    @Column(name = "payment_regime_pleaser_service_priority_id", columnDefinition = "char(36)")
    private String paymentRegimePleaserServicePriorityId;
    @Column(name = "payment_regime_title")
    private String paymentRegimeTitle;
    @Column(name = "payment_regime_description")
    private String paymentRegimeDescription;


    public TblPaymentRegime(String paymentRegimeTitle, String paymentRegimeDescription, String priorityId) {
        this.paymentRegimeTitle = paymentRegimeTitle;
        this.paymentRegimeDescription = paymentRegimeDescription;
        this.paymentRegimePleaserServicePriorityId = priorityId;
    }


    public TblPaymentRegime(String id, String paymentRegimeTitle, String paymentRegimeDescription, String priorityId) {
        this.paymentRegimeId = id;
        this.paymentRegimeTitle = paymentRegimeTitle;
        this.paymentRegimeDescription = paymentRegimeDescription;
        this.paymentRegimePleaserServicePriorityId = priorityId;
    }

}
