package com.paya.pleaserservice.service.payment;

import com.paya.pleaserservice.dto.PleaserPaymentMethodDto;
import com.paya.pleaserservice.entity.TblPleaserPriorityPaymentMethod;
import com.paya.pleaserservice.repository.PleaserPaymentMethodRepository;
import com.paya.pleaserservice.service.PaymentRegimeService;
import com.paya.pleaserservice.service.priority.PriorityServiceBridge;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentMethodServiceBridge {
    private final PleaserPaymentMethodRepository repository;
    private final PriorityServiceBridge priorityService;
    private final PaymentRegimeService regimeService;


    public PleaserPaymentMethodDto get(String priorityId) {
        PleaserPaymentMethodDto payment = priorityService.getPaymentFields(priorityId);
        payment.setRegimes(regimeService.getAll(priorityId));
        payment.setPaymentMethods(findAll(priorityId));

        return payment;
    }


    private List<TblPleaserPriorityPaymentMethod> findAll(String priorityId) {
        return repository.findAllByPleaserPriorityPaymentMethodPleaserServicePriorityId(priorityId);
    }

}
