package com.paya.pleaserservice.service.priority;

import com.paya.pleaserservice.dto.PleaserPaymentMethodDto;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import com.paya.pleaserservice.repository.PleaserServicePriorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PriorityServiceBridge {
    private final PleaserServicePriorityRepository repository;


    public PleaserPaymentMethodDto getPaymentFields(String priorityId) {
        return repository.getPaymentFields(priorityId);
    }


    public List<TblPleaserServicePriority> findAllBy(String serviceId) {
        return repository.findAllByPleaserServicePriorityPleaserServiceId(serviceId);
    }


    @Transactional
    public void deleteAllByServiceId(String id) {
        if (repository.deleteAllByServiceId(id) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "حذف اولویت ها با مشکل مواجه شد!");
    }

}