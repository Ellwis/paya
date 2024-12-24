package com.paya.pleaserservice.service;

import com.paya.pleaserservice.entity.TblPaymentRegime;
import com.paya.pleaserservice.repository.PaymentRegimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentRegimeService {
    private final PaymentRegimeRepository repository;


    public TblPaymentRegime create(TblPaymentRegime paymentRegime) {
        if (paymentRegime.getPaymentRegimeTitle() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return save(paymentRegime);
    }


    public List<TblPaymentRegime> getAll(String priorityId) {
        return repository.findAllByPaymentRegimePleaserServicePriorityId(priorityId);
    }


    private TblPaymentRegime save(TblPaymentRegime paymentRegime) {
        return repository.save(paymentRegime);
    }


    public void deleteAll(String priorityId) {
        repository.deleteByPaymentRegimePleaserServicePriorityId(priorityId);
    }


    public boolean existsByPriority(String priorityId) {
        return repository.existsByPaymentRegimePleaserServicePriorityId(priorityId);
    }

}
