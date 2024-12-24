package com.paya.pleaserservice.repository;


import com.paya.pleaserservice.entity.TblPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelRepository extends JpaRepository<TblPersonnel, String>, JpaSpecificationExecutor<TblPersonnel> {

    List<TblPersonnel> findTblPersonnelByPersonnelId(String id);

    List<TblPersonnel> findTblPersonnelByPersonnelFirstName(String firstName);

    List<TblPersonnel> findTblPersonnelByPersonnelLastName(String lastName);

    List<TblPersonnel> findTblPersonnelByPersonnelEnterpriseId(String enterpriseId);

    List<TblPersonnel> findTblPersonnelByPersonnelNationalCode(String nationalCode);


}
