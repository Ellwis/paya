package com.paya.pleaserservice.mapper;


import com.paya.pleaserservice.entity.TblPleaserPriorityPaymentMethod;
import com.paya.pleaserservice.model.PleasurePriorityPaymentMethodModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PleasurePriorityPaymentMethodMapper {
    PleasurePriorityPaymentMethodModel convertToModel(TblPleaserPriorityPaymentMethod entity);

    TblPleaserPriorityPaymentMethod convertToEntityModel(PleasurePriorityPaymentMethodModel model);

    List<PleasurePriorityPaymentMethodModel> convertToModelList(List<TblPleaserPriorityPaymentMethod> entities);

    List<TblPleaserPriorityPaymentMethod> convertToEntityList(List<PleasurePriorityPaymentMethodModel> models);

    @Mapping(target = "pleaserPriorityPaymentMethodId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPleaserPriorityPaymentMethod entity, PleasurePriorityPaymentMethodModel model);
}
