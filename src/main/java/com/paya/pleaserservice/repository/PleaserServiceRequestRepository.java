package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPleaserServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PleaserServiceRequestRepository extends JpaRepository<TblPleaserServiceRequest, String> {
    List<TblPleaserServiceRequest> findAllByPleaserServiceRequestPleaserServicePriorityId(String id);
}
