package com.paya.pleaserservice.service.v2.impl;


import com.paya.pleaserservice.core.GeneralSearchModel;
import com.paya.pleaserservice.entity.TblPleaserServiceRequestConfirmation;
import com.paya.pleaserservice.mapper.PleasureServiceRequestConfirmationMapper;
import com.paya.pleaserservice.model.PleasureServiceRequestConfirmationModel;
import com.paya.pleaserservice.repository.v2.RequestConfirmationDAO;
import com.paya.pleaserservice.service.v2.RequestConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RequestConfirmationServiceImpl implements RequestConfirmationService {
    @Autowired
    RequestConfirmationDAO requestConfirmationDAO;
    @Autowired
    PleasureServiceRequestConfirmationMapper pleasureServiceRequestConfirmationMapper;

    @Override
    public List<PleasureServiceRequestConfirmationModel> getList(GeneralSearchModel searchModel) {
        if (searchModel != null) {
            List<TblPleaserServiceRequestConfirmation> entityList = requestConfirmationDAO.getList(searchModel.getFilters(), searchModel.getSorting(), searchModel.getPageSize(), searchModel.getPageNumber());
            return pleasureServiceRequestConfirmationMapper.convertToModelList(entityList);

        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "پارامتر ورودی نمی تواند خالی باشد !");
    }
}
