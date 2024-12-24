package com.paya.pleaserservice.controller.expert;

import com.paya.pleaserservice.dto.PleaserPaymentMethodDto;
import com.paya.pleaserservice.dto.request.PleaserPaymentMethodRequestDto;
import com.paya.pleaserservice.dto.request.PleaserPaymentMethodUpdateRequestDto;
import com.paya.pleaserservice.service.payment.PleaserPaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pleaser-services/priorities/{priority_id}/")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class PleaserPaymentMethodController {
    private final PleaserPaymentMethodService service;


    @PostMapping("payment-methods")
    public PleaserPaymentMethodDto create(
            @PathVariable("priority_id") String priorityId,
            @RequestBody PleaserPaymentMethodRequestDto paymentMethod
    ) {
        return service.create(priorityId, paymentMethod);
    }


    @GetMapping("payment-methods")
    public PleaserPaymentMethodDto get(
            @PathVariable("priority_id") String priorityId
    ) {
        return service.get(priorityId);
    }


    @PutMapping("payment-methods")
    public PleaserPaymentMethodDto update(
            @PathVariable("priority_id") String priorityId,
            @RequestBody PleaserPaymentMethodUpdateRequestDto paymentMethod
    ) {
        return service.update(priorityId, paymentMethod);
    }

}
