package com.paya.pleaserservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_personnel")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TblPersonnel {

    @Id
    @Column(name = "personnel_id", columnDefinition = "char(36)")
    private String personnelId;

    @PrePersist
    public void generateId() {
        if (personnelId == null) {
            personnelId = UUID.randomUUID().toString();
        }
    }
    @Column(name = "personnel_first_name")
    private String personnelFirstName;

    @Column(name = "personnel_last_name")
    private String personnelLastName;


    @Column(name = "personnel_enterprise_id")
    private String personnelEnterpriseId;

    @Column(name = "personnel_national_code")
    private String personnelNationalCode;


    public TblPersonnel(String personnelFirstName, String personnelLastName, String personnelEnterpriseId, String personnelNationalCode) {
        this.personnelFirstName = personnelFirstName;
        this.personnelLastName = personnelLastName;
        this.personnelEnterpriseId = personnelEnterpriseId;
        this.personnelNationalCode = personnelNationalCode;
    }

}
