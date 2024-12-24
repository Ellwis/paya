package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_pleaser_service_field")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPleaserServiceField {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pleaser_service_field_id", columnDefinition = "char(36)")
    private String pleaserServiceFieldId;

    @PrePersist
    private void generateId() {
        if (pleaserServiceFieldId == null) {
            pleaserServiceFieldId = UUID.randomUUID().toString();
        }
    }

    @Column(name = "pleaser_service_field_pleaser_service_id", columnDefinition = "char(36)")
    private String PleaserServiceFieldPleaserServiceId;
    @Column(name = "pleaser_service_field_title")
    private String pleaserServiceFieldTitle;

}
