package com.paya.pleaserservice.controller.v2;


import com.paya.pleaserservice.core.GeneralSearchModel;
import com.paya.pleaserservice.model.PleasureServiceRequestConfirmationModel;
import com.paya.pleaserservice.service.v2.RequestConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/requestConfirmation")
public class RequestConfirmationController {
    @Autowired
    RequestConfirmationService requestConfirmationService;

    @GetMapping("/getFilteredList")
    public ResponseEntity<List<PleasureServiceRequestConfirmationModel>> getList(@RequestBody GeneralSearchModel searchModel) throws Exception {
        try {
            List<PleasureServiceRequestConfirmationModel> modelList = requestConfirmationService.getList(searchModel);
            return ResponseEntity.ok().body(modelList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
