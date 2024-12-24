package com.paya.pleaserservice.service.request;

import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.request.PleaserServiceRequestRequestDto;
import com.paya.pleaserservice.entity.TblPleaserServiceRequest;
import com.paya.pleaserservice.entity.TblPleaserServiceRequestField;
import com.paya.pleaserservice.repository.PleaserServiceRequestFieldRepository;
import com.paya.pleaserservice.repository.PleaserServiceRequestRepository;
import com.paya.pleaserservice.service.PaymentRegimeService;
import com.paya.pleaserservice.service.PleaserServiceFieldService;
import com.paya.pleaserservice.service.payment.PleaserPaymentMethodService;
import com.paya.pleaserservice.service.priority.PleaserServicePriorityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PleaserServiceRequestService {
    private final PleaserServiceRequestRepository repository;
    private final PleaserServicePriorityService priorityService;
    private final PleaserServiceRequestFieldRepository requestFieldRepository;
    private final PleaserServiceFieldService fieldService;
    private final PaymentRegimeService paymentRegimeService;
    private final PleaserPaymentMethodService paymentMethodService;


    // TODO : Get personnel id from token
    @Transactional
    public GenericMessageDto request(
            String priorityId,
            PleaserServiceRequestRequestDto request
    ) {
        // check service
        if (!priorityService.existsById(priorityId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "خدمت موردنظر یافت نشد!");
        priorityService.checkAllowedForRequest(priorityId);

        // TODO : check personnel had a request for this priority

        TblPleaserServiceRequest serviceRequest = save(request.toRequest(priorityId));

        // check fields
        fieldService.checkExistsFieldAndContentsInPriority(priorityId, request.getPleaserServiceRequestFieldPleaserServiceFieldContentIds());

        request.getPleaserServiceRequestFieldPleaserServiceFieldContentIds().forEach(contentId -> {
            TblPleaserServiceRequestField field = new TblPleaserServiceRequestField();
            field.setPleaserServiceRequestFieldPleaserServiceRequestId(serviceRequest.getPleaserServiceRequestId());
            field.setPleaserServiceRequestFieldPleaserServiceFieldContentId(contentId);
            requestFieldRepository.save(field);
        });

        // check payment method
        paymentMethodService.findById(request.getPleaserServiceRequestPleaserPriorityPaymentMethodId());

        if (request.getPleaserServiceRequestPaymentRegimeId() != null && !paymentRegimeService.existsByPriority(priorityId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "این رژیم وجود ندارد!");

        if (serviceRequest.getPleaserServiceRequestCount() <= 0 || priorityService.findById(priorityId).getPleaserServicePriorityAllowedCount() < serviceRequest.getPleaserServiceRequestCount())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "مقدار درخواستی مجاز نیست!");

        return new GenericMessageDto("درخواست شما با موفقیت ثبت شد!");
    }


//    public Page<PersonnelServiceRequestDto> getPersonnelRequest(String personnelId) {
//
//    }


    private TblPleaserServiceRequest save(TblPleaserServiceRequest request) {
        return repository.save(request);
    }


    public List<TblPleaserServiceRequest> getRequests(String priorityId) {
        return repository.findAllByPleaserServiceRequestPleaserServicePriorityId(priorityId);
    }

}
