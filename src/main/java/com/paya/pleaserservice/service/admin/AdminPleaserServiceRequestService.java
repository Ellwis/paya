package com.paya.pleaserservice.service.admin;

import com.paya.pleaserservice.entity.TblPersonnel;
import com.paya.pleaserservice.entity.TblPleaserServiceRequest;
import com.paya.pleaserservice.service.request.PleaserServiceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminPleaserServiceRequestService {
    private final PleaserServiceRequestService service;


    public List<TblPersonnel> getRequests(String priorityId) {
        List<TblPleaserServiceRequest> requests = service.getRequests(priorityId);
        // TODO : get personnel of request
        return requests.stream()
                .map(request -> new TblPersonnel("اسم کوچک", "نام خانوادگی", "23424", "354154"))
                .toList();
    }

}
