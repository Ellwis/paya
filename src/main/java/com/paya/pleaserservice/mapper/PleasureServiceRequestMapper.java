package com.paya.pleaserservice.mapper;

import com.paya.pleaserservice.entity.TblPleaserServiceRequest;
import com.paya.pleaserservice.model.PleasureServiceRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PleasureServiceRequestMapper {
    PleasureServiceRequestModel convertToModel(TblPleaserServiceRequest entity);

    TblPleaserServiceRequest convertToEntity(PleasureServiceRequestModel model);

    List<PleasureServiceRequestModel> convertToModelList(List<TblPleaserServiceRequest> entityList);

    List<TblPleaserServiceRequest> convertToEntityList(List<PleasureServiceRequestModel> modelList);

    @Mapping(target = "pleaserServiceRequestId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPleaserServiceRequest entity, PleasureServiceRequestModel model);
}
