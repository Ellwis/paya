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
public class PleaserPaymentMethodDto {
    private List<TblPleaserPriorityPaymentMethod> paymentMethods;
    private BigDecimal pleaserServicePriorityPrepayment;
    private Integer pleaserServicePriorityCountOfMonthInstallments;
    private BigDecimal pleaserServicePriorityAmount;
    private List<TblPaymentRegime> regimes;


    public PleaserPaymentMethodDto(
            BigDecimal pleaserServicePriorityPrepayment,
            Integer pleaserServicePriorityCountOfMonthInstallments,
            BigDecimal pleaserServicePriorityAmount
    ) {
        this.pleaserServicePriorityPrepayment = pleaserServicePriorityPrepayment;
        this.pleaserServicePriorityCountOfMonthInstallments = pleaserServicePriorityCountOfMonthInstallments;
        this.pleaserServicePriorityAmount = pleaserServicePriorityAmount;
    }

}
