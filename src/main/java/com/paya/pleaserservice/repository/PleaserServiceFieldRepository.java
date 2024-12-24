package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPleaserServiceField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PleaserServiceFieldRepository extends JpaRepository<TblPleaserServiceField, String> {

    @Query("select f from TblPleaserServiceField f where f.PleaserServiceFieldPleaserServiceId = ?1")
    List<TblPleaserServiceField> findAllByPleaserServiceId(String id);

    @Query("SELECT CASE WHEN EXISTS (select 1 from TblPleaserServiceField tb where tb.PleaserServiceFieldPleaserServiceId = ?1 and tb.pleaserServiceFieldTitle = ?2)" +
            " THEN true" +
            " ELSE false" +
            " END AS result")
    boolean existsByServiceIdAndTitle(String serviceId, String title);

}
