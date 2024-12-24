package com.paya.pleaserservice.repository;


import com.paya.pleaserservice.entity.TblPaymentRegime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRegimeRepository extends JpaRepository<TblPaymentRegime, String> {
    List<TblPaymentRegime> findAllByPaymentRegimePleaserServicePriorityId(String priorityId);

    void deleteByPaymentRegimePleaserServicePriorityId(String priorityId);

    boolean existsByPaymentRegimePleaserServicePriorityId(String priorityId);
}
