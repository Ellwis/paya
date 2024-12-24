package com.paya.pleaserservice.service.v2;


import com.paya.pleaserservice.core.GeneralSearchModel;
import com.paya.pleaserservice.model.PleasureServicePriorityModel;
import com.paya.pleaserservice.model.PleasureServiceRequestConfirmationModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestConfirmationService {
    List<PleasureServiceRequestConfirmationModel> getList(GeneralSearchModel searchModel);
}
