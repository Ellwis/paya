package com.paya.pleaserservice.mapper;


import com.paya.pleaserservice.entity.TblPaymentRegime;
import com.paya.pleaserservice.model.PaymentRegimeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentRegimeMapper {
    PaymentRegimeModel convertToModel(TblPaymentRegime entity);

    TblPaymentRegime convertToEntity(PaymentRegimeModel model);

    List<PaymentRegimeModel> convertToModelList(List<TblPaymentRegime> entities);

    List<PaymentRegimeModel> convertToEntityList(List<PaymentRegimeModel> entities);

    @Mapping(target = "paymentRegimeId", ignore = true)
    void updateEntityWithModel(@MappingTarget TblPaymentRegime tblPaymentRegime, PaymentRegimeModel paymentRegimeModel);
}
