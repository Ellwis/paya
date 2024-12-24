package com.paya.pleaserservice.mapper;


import com.paya.pleaserservice.entity.TblPleaserServiceRequestField;
import com.paya.pleaserservice.model.PleasureServiceRequestFieldModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PleasureServiceRequestFieldMapper {
    PleasureServiceRequestFieldModel convertToModel(TblPleaserServiceRequestField entity);

    TblPleaserServiceRequestField convertToEntity(PleasureServiceRequestFieldModel model);

    List<PleasureServiceRequestFieldModel> convertToModelList(List<TblPleaserServiceRequestField> entityList);

    List<TblPleaserServiceRequestField> convertToEntityList(List<PleasureServiceRequestFieldModel> modelList);

    @Mapping(target = "pleaserServiceRequestFieldId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPleaserServiceRequestField entity, PleasureServiceRequestFieldModel model);
}
