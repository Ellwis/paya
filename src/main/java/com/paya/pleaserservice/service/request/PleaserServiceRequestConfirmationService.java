package com.paya.pleaserservice.service.request;

import com.paya.pleaserservice.entity.TblPleaserServiceRequestConfirmation;
import com.paya.pleaserservice.repository.PleaserServiceRequestConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PleaserServiceRequestConfirmationService {
    private final PleaserServiceRequestConfirmationRepository repository;


    public TblPleaserServiceRequestConfirmation findBy(String personnelId) {
        return repository.findByPleaserServiceRequestConfirmationReviewerPersonnelId(personnelId)
                .orElse(null);
    }

}
