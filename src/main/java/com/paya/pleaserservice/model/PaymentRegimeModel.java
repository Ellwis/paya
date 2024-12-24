package com.paya.pleaserservice.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRegimeModel {
    private String paymentRegimeId;
    private String paymentRegimePleaserServicePriorityId;
    private String paymentRegimeTitle;
    private String paymentRegimeDescription;
}
