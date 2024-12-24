package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPleaserServiceRequestField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PleaserServiceRequestFieldRepository extends JpaRepository<TblPleaserServiceRequestField, String> {
}
