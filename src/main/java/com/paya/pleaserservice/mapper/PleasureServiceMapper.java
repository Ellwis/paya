//package com.paya.pleaserservice.mapper;
//
//
//import com.paya.pleaserservice.entity.TblPleaserService;
//import com.paya.pleaserservice.model.PleasureServiceModel;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface PleasureServiceMapper {
//    PleasureServiceModel convertToModel(TblPleaserService entity);
//
//    TblPleaserService convertToEntity(PleasureServiceModel model);
//
//    List<PleasureServiceModel> convertToModelList(List<TblPleaserService> entityList);
//
//    List<TblPleaserService> convertToEntityList(List<PleasureServiceModel> modelList);
//
//    @Mapping(target = "pleaserServiceId", ignore = true)
//    void updateEntityWithModel(@MappingTarget TblPleaserService entity, PleasureServiceModel model);
//
//}
