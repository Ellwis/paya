package com.paya.pleaserservice.dto.request;


import com.paya.pleaserservice.entity.TblPleaserServiceRequest;
import lombok.Data;

import java.util.List;

@Data
public class PleaserServiceRequestRequestDto {
    private String pleaserServiceRequestPleaserPriorityPaymentMethodId;
    private String pleaserServiceRequestPaymentRegimeId;
    private List<String> pleaserServiceRequestFieldPleaserServiceFieldContentIds;
    private int pleaserServiceRequestCount;


    public TblPleaserServiceRequest toRequest(String priorityId) {
        TblPleaserServiceRequest request = new TblPleaserServiceRequest();
        request.setPleaserServiceRequestPleaserServicePriorityId(priorityId);
        request.setPleaserServiceRequestPaymentRegimeId(pleaserServiceRequestPaymentRegimeId);
        request.setPleaserServiceRequestPleaserPriorityPaymentMethodId(pleaserServiceRequestPleaserPriorityPaymentMethodId);
        request.setPleaserServiceRequestCount(pleaserServiceRequestCount);

        return request;
    }

}
