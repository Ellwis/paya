package com.paya.pleaserservice.service.payment;

import com.paya.pleaserservice.dto.PleaserPaymentMethodDto;
import com.paya.pleaserservice.dto.request.PleaserPaymentMethodRequestDto;
import com.paya.pleaserservice.dto.request.PleaserPaymentMethodUpdateRequestDto;
import com.paya.pleaserservice.entity.TblPleaserPriorityPaymentMethod;
import com.paya.pleaserservice.repository.PleaserPaymentMethodRepository;
import com.paya.pleaserservice.service.PaymentRegimeService;
import com.paya.pleaserservice.service.priority.PleaserServicePriorityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PleaserPaymentMethodService {
    private final PleaserPaymentMethodRepository repository;
    private final PleaserServicePriorityService priorityService;
    private final PaymentRegimeService regimeService;


    @Transactional
    public PleaserPaymentMethodDto create(String priorityId, PleaserPaymentMethodRequestDto paymentMethod) {
        if (!priorityService.existsById(priorityId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "اولویت یافت نشد!");
        if ((paymentMethod.getRegimes() != null && !paymentMethod.getRegimes().isEmpty()) && (paymentMethod.getPleaserServicePriorityPrepayment() != null))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "نمی توانید رژیم و پیش پرداخت را با هم ست کنید!");

        setPrepayment(paymentMethod);
        createRegime(priorityId, paymentMethod);
        saveAll(paymentMethod.toPaymentMethods(priorityId));

        return get(priorityId);
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PleaserPaymentMethodDto update(String priorityId, PleaserPaymentMethodUpdateRequestDto paymentMethod) {
        deleteAll(priorityId);
        deleteAllRegimes(priorityId);

        if (!priorityService.existsById(priorityId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "اولویت یافت نشد!");
        if ((paymentMethod.getRegimes() != null && !paymentMethod.getRegimes().isEmpty()) && (paymentMethod.getPleaserServicePriorityPrepayment() != null))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "نمی توانید رژیم و پیش پرداخت را با هم ست کنید!");

        setPrepayment(paymentMethod);
        createRegime(priorityId, paymentMethod);
        saveAll(paymentMethod.getPaymentMethods(), priorityId);

        return get(priorityId);
    }


    @Transactional
    public void deleteAllRegimes(String priorityId) {
        regimeService.deleteAll(priorityId);
    }


    public PleaserPaymentMethodDto get(String priorityId) {
        PleaserPaymentMethodDto payment = priorityService.getPaymentFields(priorityId);
        payment.setRegimes(regimeService.getAll(priorityId));
        payment.setPaymentMethods(findAll(priorityId));

        return payment;
    }


    private void createRegime(String priorityId, PleaserPaymentMethodUpdateRequestDto paymentMethod) {
        paymentMethod
                .toRegimes(priorityId)
                .forEach(regimeService::create);
    }


    private void createRegime(String priorityId, PleaserPaymentMethodRequestDto paymentMethod) {
        paymentMethod
                .toRegimes(priorityId)
                .forEach(regimeService::create);
    }


    private void setPrepayment(PleaserPaymentMethodUpdateRequestDto paymentMethod) {
        if (paymentMethod.getPleaserPriorityPaymentMethodTypes().stream().anyMatch(method -> method > 0)) {
            checkPrepayment(paymentMethod);
            if (paymentMethod.getPleaserServicePriorityPrepayment() != null && paymentMethod.getPleaserServicePriorityCountOfMonthInstallments() != null) {
                priorityService.updatePaymentFields(paymentMethod.getPleaserServicePriorityPrepayment(), paymentMethod.getPleaserServicePriorityCountOfMonthInstallments());
            }
        }
    }


    private void setPrepayment(PleaserPaymentMethodRequestDto paymentMethod) {
        if (paymentMethod.getPleaserPriorityPaymentMethodTypes().stream().anyMatch(method -> method > 0)) {
            checkPrepayment(paymentMethod);
            if (paymentMethod.getPleaserServicePriorityPrepayment() != null && paymentMethod.getPleaserServicePriorityCountOfMonthInstallments() != null) {
                priorityService.updatePaymentFields(paymentMethod.getPleaserServicePriorityPrepayment(), paymentMethod.getPleaserServicePriorityCountOfMonthInstallments());
            }
        }
    }


    private void checkPrepayment(PleaserPaymentMethodUpdateRequestDto paymentMethod) {
        if (paymentMethod.getPleaserServicePriorityPrepayment() != null && paymentMethod.getPleaserServicePriorityCountOfMonthInstallments() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "تعداد ماه پیش پرداخت را مشخص کنید!");
        if (paymentMethod.getPleaserServicePriorityPrepayment() == null && paymentMethod.getPleaserServicePriorityCountOfMonthInstallments() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "مقدار پیش پرداخت را مشخص کنید!");
    }


    private void checkPrepayment(PleaserPaymentMethodRequestDto paymentMethod) {
        if (paymentMethod.getPleaserServicePriorityPrepayment() != null && paymentMethod.getPleaserServicePriorityCountOfMonthInstallments() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "تعداد ماه پیش پرداخت را مشخص کنید!");
        if (paymentMethod.getPleaserServicePriorityPrepayment() == null && paymentMethod.getPleaserServicePriorityCountOfMonthInstallments() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "مقدار پیش پرداخت را مشخص کنید!");
    }


    private List<TblPleaserPriorityPaymentMethod> findAll(String priorityId) {
        return repository.findAllByPleaserPriorityPaymentMethodPleaserServicePriorityId(priorityId);
    }


    private void saveAll(List<TblPleaserPriorityPaymentMethod> paymentMethods, String priorityId) {
        paymentMethods.forEach(paymentMethod -> paymentMethod.setPleaserPriorityPaymentMethodPleaserServicePriorityId(priorityId));
        repository.saveAll(paymentMethods);
    }


    private void saveAll(List<TblPleaserPriorityPaymentMethod> paymentMethods) {
        repository.saveAll(paymentMethods);
    }


    private void deleteAll(String priorityId) {
        repository.deleteByPleaserPriorityPaymentMethodPleaserServicePriorityId(priorityId);
    }


    public TblPleaserPriorityPaymentMethod findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "نوع پرداخت یافت نشد!"));
    }


    public void checkNotExistsById(String id) {
        if (existsById(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "این نوع پرداخت وجود ندارد!");
    }


    private Boolean existsById(String id) {
        return repository.existsById(id);
    }

}
