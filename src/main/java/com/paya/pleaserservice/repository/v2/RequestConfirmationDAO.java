package com.paya.pleaserservice.repository.v2;

import com.paya.pleaserservice.entity.TblPleaserServiceRequestConfirmation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface RequestConfirmationDAO {
    List<TblPleaserServiceRequestConfirmation> getList(HashMap<String, Object> filters, HashMap<String, String> sorting, Integer pageSize, Integer pageNumber);
}
