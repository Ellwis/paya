package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.dto.PleaserPaymentMethodDto;
import com.paya.pleaserservice.dto.PleaserServicePriorityDetailDto;
import com.paya.pleaserservice.dto.PriorityReviewDto;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface PleaserServicePriorityRepository extends JpaRepository<TblPleaserServicePriority, String> {

    @Query(value = "select new com.paya.pleaserservice.dto.PleaserServicePriorityDetailDto(" +
            "s.pleaserServiceTitle," +
            "p.pleaserServicePriorityNumber," +
            "p.pleaserServicePriorityWeight," +
            "p.pleaserServicePriorityAmount," +
            "p.pleaserServicePriorityAllowedCount," +
            "p.pleaserServicePriorityStartDate," +
            "p.pleaserServicePriorityEndDate," +
            "p.pleaserServicePriorityCancelOption," +
            "p.pleaserServicePriorityMaximumCancellationDate," +
            "p.pleaserServicePriorityDescription," +
            "p.pleaserServicePriorityMeasurementUnit" +
            ") " +
            "from TblPleaserServicePriority p,TblPleaserService s where p.pleaserServicePriorityId = :priorityId and s.pleaserServiceId = p.pleaserServicePriorityPleaserServiceId")
    PleaserServicePriorityDetailDto getPriority(@Param("priorityId") String priorityId);


    List<TblPleaserServicePriority> findAllByPleaserServicePriorityPleaserServiceId(String serviceId);

    @Transactional
    @Modifying
    @Query("update TblPleaserServicePriority set pleaserServicePriorityPrepayment = ?1, pleaserServicePriorityCountOfMonthInstallments = ?2")
    int updatePaymentFields(BigDecimal prepayment, Integer countOfMonthInstallments);

    @Query("select new com.paya.pleaserservice.dto.PleaserPaymentMethodDto(p.pleaserServicePriorityPrepayment,p.pleaserServicePriorityCountOfMonthInstallments,p.pleaserServicePriorityAmount) from TblPleaserServicePriority p where p.pleaserServicePriorityId = ?1")
    PleaserPaymentMethodDto getPaymentFields(String priorityId);

    Boolean existsByPleaserServicePriorityNumberAndPleaserServicePriorityPleaserServiceId(Integer number, String serviceId);

    @Transactional
    @Modifying
    @Query("update TblPleaserServicePriority set pleaserServicePriorityIsDraft = false where pleaserServicePriorityId = ?1")
    int changeDraftStatus(String priorityId);

    @Transactional
    @Modifying
    @Query("delete from TblPleaserServicePriority where pleaserServicePriorityPleaserServiceId = ?1")
    int deleteAllByServiceId(String priorityId);

    @Query("select p from TblPleaserServicePriority  p where p.pleaserServicePriorityPleaserServiceId = ?1 order by p.pleaserServicePriorityStartDate desc limit 1")
    Optional<TblPleaserServicePriority> findLastPriority(String pleaserServiceId);

    @Query("select new com.paya.pleaserservice.dto.PriorityReviewDto(" +
            "p.pleaserServicePriorityId," +
            "p.pleaserServicePriorityWeight," +
            "p.pleaserServicePriorityAmount," +
            "p.pleaserServicePriorityNumber," +
            "c.pleaserServicePriorityConfirmationConfirm" +
            ") from TblPleaserServicePriority p Left join TblPleaserServicePriorityConfirmation c on c.pleaserServicePriorityConfirmationPleaserServicePriorityId = p.pleaserServicePriorityId" +
            " where p.pleaserServicePriorityPleaserServiceId = ?1"
    )
    List<PriorityReviewDto> getPriorityReviews(String pleaserServiceId);
}

