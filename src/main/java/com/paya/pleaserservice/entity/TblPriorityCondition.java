package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_priority_condition")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPriorityCondition {
    @Id
    @Column(name = "priority_condition_id", columnDefinition = "char(36)")
    private String priorityConditionId;

    @PrePersist
    private void generateId() {
        if (priorityConditionId == null) {
            priorityConditionId = UUID.randomUUID().toString();
        }
    }

    @Column(name = "priority_condition_pleaser_service_priority_id", columnDefinition = "char(36)")
    private String priorityConditionPleaserServicePriorityId;
    @Column(name = "priority_condition_title")
    private String priorityConditionTitle;
    @Column(name = "priority_condition_field_title")
    private String priorityConditionFieldTitle;
    @Column(name = "priority_condition_status")
    private int priorityConditionStatus;
    @Column(name = "priority_condition_content")
    private String priorityConditionContent;
    @Column(name = "priority_condition_has_condition", columnDefinition = "bit")
    private boolean priorityConditionHasCondition;

}
