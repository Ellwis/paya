package com.paya.pleaserservice.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentModel {
    private String attachmentId;
    private String attachmentFile;
    private String attachmentName;
    private String attachmentPaymentRegimeId;
    private String attachmentPleaserServicePriorityId;
    private String attachmentPleaserServiceId;

}
