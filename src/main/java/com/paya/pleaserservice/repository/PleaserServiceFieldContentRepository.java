package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPleaserServiceFieldContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PleaserServiceFieldContentRepository extends JpaRepository<TblPleaserServiceFieldContent, String> {
    List<TblPleaserServiceFieldContent> findAllByPleaserServiceFieldContentPleaserServiceFieldId(String fieldId);

    void deleteByPleaserServiceFieldContentPleaserServiceFieldId(String fieldId);

    @Query("SELECT CASE WHEN EXISTS (select 1 from TblPleaserServiceFieldContent tb where tb.pleaserServiceFieldContentPleaserServiceFieldId = ?1 and tb.pleaserServiceFieldContentTitle = ?2)" +
            " THEN true" +
            " ELSE false" +
            " END AS result")
    boolean existsByFieldIdAndTitle(String fieldId, String title);
}
