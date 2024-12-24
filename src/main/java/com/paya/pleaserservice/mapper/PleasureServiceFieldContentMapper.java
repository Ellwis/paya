package com.paya.pleaserservice.mapper;


import com.paya.pleaserservice.entity.TblPleaserServiceFieldContent;
import com.paya.pleaserservice.model.PleasureServiceFieldContentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PleasureServiceFieldContentMapper {
    PleasureServiceFieldContentModel convertToModel(TblPleaserServiceFieldContent entity);

    TblPleaserServiceFieldContent convertToEntity(PleasureServiceFieldContentModel model);

    List<PleasureServiceFieldContentModel> convertToModelList(List<TblPleaserServiceFieldContent> entityList);

    List<TblPleaserServiceFieldContent> convertToEntityList(List<PleasureServiceFieldContentModel> modelList);

    @Mapping(target = "pleaserServiceFieldContentId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPleaserServiceFieldContent entity, PleasureServiceFieldContentModel model);
}
