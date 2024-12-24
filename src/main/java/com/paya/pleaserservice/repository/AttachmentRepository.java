package com.paya.pleaserservice.repository;

import com.paya.pleaserservice.entity.TblAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<TblAttachment, String> {

    List<TblAttachment> findAllByAttachmentPaymentRegimeId(String paymentRegimeId);
    List<TblAttachment> findAllByAttachmentPleaserServiceId(String pleaserServiceId);
    List<TblAttachment> findAllByAttachmentPleaserServicePriorityId(String pleaserServicePriorityId);
}
