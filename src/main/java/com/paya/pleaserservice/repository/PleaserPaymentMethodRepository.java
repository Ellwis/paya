package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPleaserPriorityPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PleaserPaymentMethodRepository extends JpaRepository<TblPleaserPriorityPaymentMethod, String> {
    List<TblPleaserPriorityPaymentMethod> findAllByPleaserPriorityPaymentMethodPleaserServicePriorityId(String priorityId);

    void deleteByPleaserPriorityPaymentMethodPleaserServicePriorityId(String priorityId);
}
