package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPaymentRegime;
import com.paya.pleaserservice.entity.TblPleaserPriorityPaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AdminPleaserPaymentMethodDto {
    private List<TblPleaserPriorityPaymentMethod> paymentMethods;
    private BigDecimal pleaserServicePriorityPrepayment;
    private Integer pleaserServicePriorityCountOfMonthInstallments;
    private List<TblPaymentRegime> regimes;
    private List<AttachmentDto> attachments;


    public AdminPleaserPaymentMethodDto(PleaserPaymentMethodDto paymentMethod, List<AttachmentDto> attachments) {
        this.paymentMethods = paymentMethod.getPaymentMethods();
        this.pleaserServicePriorityPrepayment = paymentMethod.getPleaserServicePriorityPrepayment();
        this.pleaserServicePriorityCountOfMonthInstallments = paymentMethod.getPleaserServicePriorityCountOfMonthInstallments();
        this.regimes = paymentMethod.getRegimes();
        this.attachments = attachments;
    }

}
