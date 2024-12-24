package com.paya.pleaserservice.mapper;


import com.paya.pleaserservice.entity.TblPleaserServiceField;
import com.paya.pleaserservice.model.PleasureServiceFieldModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PleasureServiceFieldMapper {
    PleasureServiceFieldModel convertToModel(TblPleaserServiceField entity);

    TblPleaserServiceField convertToEntity(PleasureServiceFieldModel model);

    List<PleasureServiceFieldModel> convertToModelList(List<TblPleaserServiceField> entityList);

    List<TblPleaserServiceField> convertToEntityList(List<PleasureServiceFieldModel> modelList);

    @Mapping(target = "pleaserServiceFieldId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPleaserServiceField entity, PleasureServiceFieldModel model);
}
