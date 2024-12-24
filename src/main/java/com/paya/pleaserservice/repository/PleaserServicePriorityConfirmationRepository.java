package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblPleaserServicePriorityConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PleaserServicePriorityConfirmationRepository extends JpaRepository<TblPleaserServicePriorityConfirmation, String> {
    /**
     * Return true if there is a record which has value of 'null' or '1' value for confirm field
     * @param serviceId
     * @return
     */
    @Query("SELECT CASE " +
            " WHEN EXISTS (" +
            " SELECT 1" +
            " FROM TblPleaserServicePriorityConfirmation pc" +
            " LEFT JOIN TblPleaserServicePriority p" +
            " ON pc.pleaserServicePriorityConfirmationPleaserServicePriorityId = p.pleaserServicePriorityId" +
            " WHERE p.pleaserServicePriorityPleaserServiceId = ?1" +
            " AND (pc.pleaserServicePriorityConfirmationConfirm = 1 " +
            " OR pc.pleaserServicePriorityConfirmationConfirm IS NULL))" +
            " THEN true" +
            " ELSE false" +
            " END AS result")
    Boolean hasEnabledPriority(String serviceId);

    Optional<TblPleaserServicePriorityConfirmation> findByPleaserServicePriorityConfirmationPleaserServicePriorityId(String priorityId);

}
