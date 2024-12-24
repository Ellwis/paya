package com.paya.pleaserservice.service;

import com.paya.pleaserservice.dto.request.PriorityConfirmationRequestDto;
import com.paya.pleaserservice.entity.TblPleaserServicePriorityConfirmation;
import com.paya.pleaserservice.repository.PleaserServicePriorityConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PleaserServicePriorityConfirmationService {
    private final PleaserServicePriorityConfirmationRepository repository;


    /**
     * Service has any accepted or pending priority
     * @param serviceId
     * @return boolean
     */
    public Boolean hasEnabledPriority(String serviceId) {
        return repository.hasEnabledPriority(serviceId);
    }


    public TblPleaserServicePriorityConfirmation findByPriorityId(String priorityId) {
        return repository.findByPleaserServicePriorityConfirmationPleaserServicePriorityId(priorityId)
                .orElse(null);
    }


    public TblPleaserServicePriorityConfirmation create(String id, PriorityConfirmationRequestDto confirmation) {
        // TODO : Set Personnel Id
        return repository.save(confirmation.toConfirmation(id));
    }

}
