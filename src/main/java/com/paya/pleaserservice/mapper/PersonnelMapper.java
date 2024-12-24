package com.paya.pleaserservice.mapper;


import com.paya.pleaserservice.entity.TblPersonnel;
import com.paya.pleaserservice.model.PersonnelModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonnelMapper {
    PersonnelModel convertToModel(TblPersonnel entity);

    TblPersonnel convertToEntity(PersonnelModel model);

    List<TblPersonnel> convertToEntityList(List<PersonnelModel> modelList);

    List<PersonnelModel> convertToModelList(List<TblPersonnel> entityList);

    @Mapping(target = "personnelId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPersonnel entity, PersonnelModel model);

}
