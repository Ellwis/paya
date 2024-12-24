package com.paya.pleaserservice.service.admin;

import com.paya.pleaserservice.dto.PleaserServiceReviewDto;
import com.paya.pleaserservice.dto.PriorityReviewDetailDto;
import com.paya.pleaserservice.entity.TblPleaserService;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import com.paya.pleaserservice.repository.PleaserServicePriorityRepository;
import com.paya.pleaserservice.service.PleaserServiceService;
import com.paya.pleaserservice.service.priority.PleaserServicePriorityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminPleaserServiceService {
    private final PleaserServiceService pleaserServiceService;
    private final PleaserServicePriorityRepository priorityRepository;
    private final PleaserServicePriorityService priorityService;


    public PleaserServiceReviewDto getPrioritiesForReview(String id) {
        TblPleaserService service = pleaserServiceService.findById(id);
        // TODO : change number of personnel(remove hard coded in PriorityReviewDto)
        return new PleaserServiceReviewDto(service, priorityRepository.getPriorityReviews(id));
    }


    public PriorityReviewDetailDto getPriorityDetailForReview(String priorityId) {
        TblPleaserServicePriority priority = priorityService.findById(priorityId);
        TblPleaserService service = pleaserServiceService.findById(priority.getPleaserServicePriorityPleaserServiceId());

        PriorityReviewDetailDto result = new PriorityReviewDetailDto(priority, service);
        // TODO : change number of personnel(remove hard coded in PriorityReviewDetailDto)
        return result;
    }

}
