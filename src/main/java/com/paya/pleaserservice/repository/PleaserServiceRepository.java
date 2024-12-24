package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPleaserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PleaserServiceRepository extends JpaRepository<TblPleaserService, String> {
    TblPleaserService findPleaserServiceByPleaserServiceId(String p_id);

    TblPleaserService getPleaserServiceByPleaserServiceTitle(String title);

    @Transactional
    @Modifying
    @Query("update TblPleaserService set pleaserServiceIsDraft = false where pleaserServiceId = ?1")
    int changeDraftStatus(String id);

//   List<TblPleaserService> findByDate(Date pleaserServiceCreatedAt);

}

