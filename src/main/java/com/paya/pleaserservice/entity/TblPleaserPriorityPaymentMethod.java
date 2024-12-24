package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_pleaser_priority_payment_method")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPleaserPriorityPaymentMethod {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pleaser_priority_payment_method_id")
    private String pleaserPriorityPaymentMethodId;


    @PrePersist
    private void generateId() {
        if (pleaserPriorityPaymentMethodId == null) {
            pleaserPriorityPaymentMethodId = UUID.randomUUID().toString();
        }
    }


    @Column(name = "pleaser_priority_payment_method_type")
    private Integer pleaserPriorityPaymentMethodType;

    @JsonIgnore
    @Column(name = "pleaser_priority_payment_method_pleaser_service_priority_id")
    private String pleaserPriorityPaymentMethodPleaserServicePriorityId;


    public TblPleaserPriorityPaymentMethod(Integer pleaserPriorityPaymentMethodType, String pleaserPriorityPaymentMethodPleaserServicePriorityId) {
        this.pleaserPriorityPaymentMethodType = pleaserPriorityPaymentMethodType;
        this.pleaserPriorityPaymentMethodPleaserServicePriorityId = pleaserPriorityPaymentMethodPleaserServicePriorityId;
    }

}
