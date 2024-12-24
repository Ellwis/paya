package com.paya.pleaserservice.mapper;


import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import com.paya.pleaserservice.model.PleasureServicePriorityModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PleasureServicePriorityMapper {
    PleasureServicePriorityModel convertToModel(TblPleaserServicePriority entity);

    TblPleaserServicePriority convertToEntity(PleasureServicePriorityModel model);

    List<PleasureServicePriorityModel> convertToModelList(List<TblPleaserServicePriority> entityList);

    List<TblPleaserServicePriority> convertToEntityList(List<PleasureServicePriorityModel> modelList);

    @Mapping(target = "pleaserServicePriorityId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPleaserServicePriority entity, PleasureServicePriorityModel model);

}
