package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblAttachment;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttachmentDto {
    private String attachmentId;
    private String attachmentName;
    private String attachmentPaymentRegimeId;
    private String attachmentPleaserServicePriorityId;
    private String attachmentPleaserServiceId;


    public AttachmentDto(TblAttachment attachment) {
        this.attachmentId = attachment.getAttachmentId();
        this.attachmentName = attachment.getAttachmentName();
        this.attachmentPaymentRegimeId = attachment.getAttachmentPaymentRegimeId();
        this.attachmentPleaserServicePriorityId = attachment.getAttachmentPleaserServicePriorityId();
        this.attachmentPleaserServiceId = attachment.getAttachmentPleaserServiceId();
    }

}
