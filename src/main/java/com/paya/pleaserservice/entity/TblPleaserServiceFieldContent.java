package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_pleaser_service_field_content")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPleaserServiceFieldContent {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pleaser_service_field_content_id", columnDefinition = "char(36)")
    private String pleaserServiceFieldContentId;

    @PrePersist
    private void generateId() {
        if (pleaserServiceFieldContentId == null) {
            pleaserServiceFieldContentId = UUID.randomUUID().toString();
        }
    }

    @Column(name = "pleaser_service_field_content_pleaser_service_field_id", columnDefinition = "char(36)")
    private String pleaserServiceFieldContentPleaserServiceFieldId;
    @Column(name = "pleaser_service_field_content_title")
    private String pleaserServiceFieldContentTitle;
}
