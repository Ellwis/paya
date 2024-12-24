package com.paya.pleaserservice.service.condition;


import com.paya.pleaserservice.entity.TblPriorityCondition;
import com.paya.pleaserservice.repository.PriorityConditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PriorityConditionServiceBridge {
    private final PriorityConditionRepository repository;

    public List<TblPriorityCondition> getAll(String priorityId) {
        return repository.findTblPriorityConditionByPriorityConditionPleaserServicePriorityId(priorityId);
    }

}
