package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPleaserPriorityPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PleaserPriorityPaymentRepository extends JpaRepository<TblPleaserPriorityPaymentMethod, String> {
}