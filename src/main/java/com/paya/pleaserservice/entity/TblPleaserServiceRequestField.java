package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_pleaser_service_request_field")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPleaserServiceRequestField {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pleaser_service_request_field_id", columnDefinition = "char(36)")
    private String pleaserServiceRequestFieldId;


    @PrePersist
    private void generateId() {
        if (pleaserServiceRequestFieldId == null) {
            pleaserServiceRequestFieldId = UUID.randomUUID().toString();
        }
    }

    @Column(name = "pleaser_service_request_field_pleaser_service_field_content_id")
    private String pleaserServiceRequestFieldPleaserServiceFieldContentId;

    @Column(name = "pleaser_service_request_field_pleaser_service_request_id")
    private String pleaserServiceRequestFieldPleaserServiceRequestId;

}
