package com.paya.pleaserservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPaymentRegime;
import com.paya.pleaserservice.entity.TblPleaserPriorityPaymentMethod;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PleaserPaymentMethodUpdateRequestDto {
    private List<TblPleaserPriorityPaymentMethod> paymentMethods;
    private BigDecimal pleaserServicePriorityPrepayment;
    private Integer pleaserServicePriorityCountOfMonthInstallments;
    private List<TblPaymentRegime> regimes;


    @JsonIgnore
    public List<Integer> getPleaserPriorityPaymentMethodTypes() {
        if (paymentMethods == null || paymentMethods.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "باید روشی پرداختی را انتخاب کنید!");
        return paymentMethods.stream().map(TblPleaserPriorityPaymentMethod::getPleaserPriorityPaymentMethodType).toList();
    }


    public List<TblPaymentRegime> toRegimes(String priorityId) {
        return regimes.stream()
                .map(paymentRegime -> new TblPaymentRegime(paymentRegime.getPaymentRegimeId(), paymentRegime.getPaymentRegimeTitle(), paymentRegime.getPaymentRegimeDescription(), priorityId))
                .toList();
    }

}
