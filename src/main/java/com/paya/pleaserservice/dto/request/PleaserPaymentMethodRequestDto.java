package com.paya.pleaserservice.dto.request;

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
public class PleaserPaymentMethodRequestDto {
    private List<Integer> pleaserPriorityPaymentMethodTypes;
    private BigDecimal pleaserServicePriorityPrepayment;
    private Integer pleaserServicePriorityCountOfMonthInstallments;
    private List<Regime> regimes;

    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private static class Regime {
        private String paymentRegimeTitle;
        private String paymentRegimeDescription;

    }


    public List<TblPleaserPriorityPaymentMethod> toPaymentMethods(String priorityId) {
        if (pleaserPriorityPaymentMethodTypes == null || pleaserPriorityPaymentMethodTypes.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "باید روشی پرداختی را انتخاب کنید!");
        return pleaserPriorityPaymentMethodTypes
                .stream()
                .filter(method -> method < 3)
                .map(method -> new TblPleaserPriorityPaymentMethod(method, priorityId))
                .toList();
    }


    public List<TblPaymentRegime> toRegimes(String priorityId) {
        return regimes.stream()
                .map(paymentRegime -> new TblPaymentRegime(paymentRegime.getPaymentRegimeTitle(), paymentRegime.getPaymentRegimeDescription(), priorityId))
                .toList();
    }

}
