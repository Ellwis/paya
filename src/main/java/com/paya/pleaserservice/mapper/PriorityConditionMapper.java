package com.paya.pleaserservice.mapper;

import com.paya.pleaserservice.entity.TblPriorityCondition;
import com.paya.pleaserservice.model.PriorityConditionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriorityConditionMapper {
    PriorityConditionModel convertToModel(TblPriorityCondition entity);

    TblPriorityCondition convertToEntity(PriorityConditionModel model);

    List<PriorityConditionModel> convertToModelList(List<TblPriorityCondition> entityList);

    List<TblPriorityCondition> convertToEntityList(List<PriorityConditionModel> modelList);

    @Mapping(target = "priorityConditionId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPriorityCondition entity, PriorityConditionModel model);
}
