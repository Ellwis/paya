package com.paya.pleaserservice.service.condition;

import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.request.PriorityConditionRequestDto;
import com.paya.pleaserservice.entity.TblPriorityCondition;
import com.paya.pleaserservice.repository.PriorityConditionRepository;
import com.paya.pleaserservice.service.priority.PleaserServicePriorityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PriorityConditionService {
    private final PriorityConditionRepository repository;
    private final PleaserServicePriorityService priorityService;


    public TblPriorityCondition create(PriorityConditionRequestDto request, String priorityId) {
        priorityService.checkUpdatable(priorityId);
        return save(request.toCondition(priorityId));
    }


    public List<TblPriorityCondition> getAll(String priorityId) {
        return repository.findTblPriorityConditionByPriorityConditionPleaserServicePriorityId(priorityId);
    }


    public TblPriorityCondition update(String id, PriorityConditionRequestDto request) {
        TblPriorityCondition condition = findById(id);
        priorityService.checkUpdatable(condition.getPriorityConditionPleaserServicePriorityId());

        if (request.getPriorityConditionContent() != null)
            condition.setPriorityConditionContent(request.getPriorityConditionContent());
        if (request.getPriorityConditionStatus() != null)
            condition.setPriorityConditionStatus(request.getPriorityConditionStatus());
        if (request.getPriorityConditionTitle() != null)
            condition.setPriorityConditionTitle(request.getPriorityConditionTitle());
        if (request.getPriorityConditionFieldTitle() != null)
            condition.setPriorityConditionFieldTitle(request.getPriorityConditionFieldTitle());

        return save(condition);
    }


    @Transactional
    public GenericMessageDto delete(String id) {
        TblPriorityCondition condition = findById(id);
        priorityService.checkUpdatable(condition.getPriorityConditionPleaserServicePriorityId());
        repository.delete(condition);

        return new GenericMessageDto("حذف شرط با موفقیت انجام شد!");
    }


    private TblPriorityCondition findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "شرط موردنظر یافت نشد!"));
    }


    private TblPriorityCondition save(TblPriorityCondition condition) {
        return repository.save(condition);
    }

}
