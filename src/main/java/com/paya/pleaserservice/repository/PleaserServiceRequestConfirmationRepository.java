package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPleaserServiceRequestConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PleaserServiceRequestConfirmationRepository extends JpaRepository<TblPleaserServiceRequestConfirmation, String> {
    Optional<TblPleaserServiceRequestConfirmation> findByPleaserServiceRequestConfirmationReviewerPersonnelId(String personnelId);
}
