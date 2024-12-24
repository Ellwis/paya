package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_pleaser_service_priority")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPleaserServicePriority {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pleaser_service_priority_id", columnDefinition = "char(36)")
    private String pleaserServicePriorityId;


    @PrePersist
    public void generateId() {
        if (pleaserServicePriorityId == null) {
            pleaserServicePriorityId = UUID.randomUUID().toString();
        }
    }


    @Column(name = "pleaser_service_priority_number", columnDefinition = "TINYINT")
    private int pleaserServicePriorityNumber;

    @Column(name = "pleaser_service_priority_pleaser_service_id", columnDefinition = "char(36)")
    private String pleaserServicePriorityPleaserServiceId;

    @Column(name = "pleaser_service_priority_weight", columnDefinition = "int")
    private int pleaserServicePriorityWeight;

    @Column(name = "pleaser_service_priority_amount")
    private BigDecimal pleaserServicePriorityAmount;

    @Column(name = "pleaser_service_priority_allowed_count", columnDefinition = "int")
    private int pleaserServicePriorityAllowedCount;

    @Column(name = "pleaser_service_priority_measurement_unit")
    private String pleaserServicePriorityMeasurementUnit;

    @Column(name = "pleaser_service_priority_start_date", columnDefinition = "Date")
    private LocalDate pleaserServicePriorityStartDate;

    @Column(name = "pleaser_service_priority_end_date", columnDefinition = "Date")
    private LocalDate pleaserServicePriorityEndDate;

    @Column(name = "pleaser_service_priority_description", length = 50)
    private String pleaserServicePriorityDescription;

    @Column(name = "pleaser_service_priority_prepayment")
    private BigDecimal pleaserServicePriorityPrepayment;

    @Column(name = "pleaser_service_priority_count_of_month_installments")
    private Integer pleaserServicePriorityCountOfMonthInstallments;

    @Column(name = "pleaser_service_priority_is_draft")
    private boolean pleaserServicePriorityIsDraft = true;

    @Column(name = "pleaser_service_priority_cancel_option", columnDefinition = "bit")
    private boolean pleaserServicePriorityCancelOption = false;

    @Column(name = "pleaser_service_priority_maximum_cancellation_date", columnDefinition = "datetime")
    private LocalDateTime pleaserServicePriorityMaximumCancellationDate;


    public boolean getPleaserServicePriorityCancelOption() {
        return pleaserServicePriorityCancelOption;
    }


}
