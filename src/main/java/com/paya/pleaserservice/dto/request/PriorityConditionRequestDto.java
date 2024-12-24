package com.paya.pleaserservice.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPriorityCondition;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PriorityConditionRequestDto {
    private String priorityConditionTitle;
    private String priorityConditionFieldTitle;
    private Integer priorityConditionStatus;
    private String priorityConditionContent;


    public TblPriorityCondition toCondition(String priorityId) {
        TblPriorityCondition condition = new TblPriorityCondition();
        condition.setPriorityConditionPleaserServicePriorityId(priorityId);
        condition.setPriorityConditionTitle(priorityConditionTitle);
        condition.setPriorityConditionFieldTitle(priorityConditionFieldTitle);
        if (priorityConditionStatus == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "فیلد وضعیت خالی هست!");
        condition.setPriorityConditionStatus(priorityConditionStatus);
        condition.setPriorityConditionContent(priorityConditionContent);
        return condition;
    }

}
