package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.dto.ServiceTypeDto;
import com.paya.pleaserservice.service.PleaserServiceService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_pleaser_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPleaserService {
    @Id
    @Column(name = "pleaser_service_id", columnDefinition = "char(36)")
    private String pleaserServiceId; // UUID as the primary key for pleaser service

    @Column(name = "pleaser_service_service_number")
    private String pleaserServiceServiceNumber; // Service number identifier

    @CreationTimestamp
    @Column(name = "pleaser_service_created_at")
    private LocalDateTime pleaserServiceCreatedAt; // Timestamp for when the service was created

    @Column(name = "pleaser_service_type", columnDefinition = "TINYINT")
    private int pleaserServiceType; // Stores the type of service as an integer

    @Column(name = "pleaser_service_is_draft")
    private Boolean pleaserServiceIsDraft = true; // Flag to indicate if the service is a draft

    @Column(name = "pleaser_service_title", length = 50)
    private String pleaserServiceTitle; // Title of the pleaser service

    @Column(name = "pleaser_service_count")
    private int pleaserServiceCount; // Count of how many times the service has been used or requested

    @JsonIgnore
    @Column(name = "pleaser_service_personnel_id", columnDefinition = "char(36)", nullable = false)
    private String pleaserServicePersonnelId;


    @JsonIgnore
    public ServiceTypeDto getPleaserServiceType() {
        return PleaserServiceService.getServiceType(pleaserServiceType);
    }

    @PrePersist
    private void generateId() {
        if (pleaserServiceId == null) {
            pleaserServiceId = UUID.randomUUID().toString();
        }
    }
}
