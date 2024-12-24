package com.paya.pleaserservice.controller.expert;

import com.paya.pleaserservice.dto.GenericMessageDto;
import com.paya.pleaserservice.dto.request.PriorityConditionRequestDto;
import com.paya.pleaserservice.entity.TblPriorityCondition;
import com.paya.pleaserservice.service.condition.PriorityConditionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pleaser-services/priorities")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class PriorityConditionController {
    private final PriorityConditionService service;


    @PostMapping("/{priority_id}/conditions")
    public TblPriorityCondition create(@PathVariable("priority_id") String priorityId, @RequestBody PriorityConditionRequestDto condition) {
        return service.create(condition, priorityId);
    }

    @GetMapping("/{priority_id}/conditions")
    public List<TblPriorityCondition> getAll(@PathVariable("priority_id") String priorityId) {
        return service.getAll(priorityId);
    }

    @PatchMapping("/conditions/{id}")
    public TblPriorityCondition update(@PathVariable("id") String id, @RequestBody PriorityConditionRequestDto condition) {
        return service.update(id, condition);
    }


    @DeleteMapping("/conditions/{id}")
    public GenericMessageDto create(@PathVariable String id) {
        return service.delete(id);
    }


}
