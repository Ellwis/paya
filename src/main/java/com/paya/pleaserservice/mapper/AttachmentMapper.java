package com.paya.pleaserservice.mapper;


import com.paya.pleaserservice.entity.TblAttachment;
import com.paya.pleaserservice.model.AttachmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    AttachmentModel convertToModel(TblAttachment entity);

    TblAttachment convertToEntity(AttachmentModel model);

    List<AttachmentModel> convertToModelList(List<TblAttachment> list);

    List<AttachmentModel> convertToEntityList(List<TblAttachment> list);

    @Mapping(target = "attachmentId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblAttachment entity, TblAttachment model);
}
