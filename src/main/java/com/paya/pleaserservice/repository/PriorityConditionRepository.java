package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPriorityCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PriorityConditionRepository extends JpaRepository<TblPriorityCondition, String> {
    List<TblPriorityCondition> findTblPriorityConditionByPriorityConditionPleaserServicePriorityId(String priorityId);

}
