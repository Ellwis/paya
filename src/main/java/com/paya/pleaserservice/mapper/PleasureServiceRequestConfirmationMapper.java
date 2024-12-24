package com.paya.pleaserservice.mapper;


import com.paya.pleaserservice.entity.TblPleaserServiceRequestConfirmation;
import com.paya.pleaserservice.model.PleasureServiceRequestConfirmationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PleasureServiceRequestConfirmationMapper {
    PleasureServiceRequestConfirmationModel convertToModel(TblPleaserServiceRequestConfirmation entity);

    TblPleaserServiceRequestConfirmation convertToEntity(PleasureServiceRequestConfirmationModel model);

    List<PleasureServiceRequestConfirmationModel> convertToModelList(List<TblPleaserServiceRequestConfirmation> entityList);

    List<TblPleaserServiceRequestConfirmation> convertToEntityList(List<PleasureServiceRequestConfirmationModel> modelList);

    @Mapping(target = "pleaserServiceRequestConfirmationId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPleaserServiceRequestConfirmation entity, PleasureServiceRequestConfirmationModel model);
}
