package com.paya.pleaserservice.dto.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserServicePriorityConfirmation;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PriorityConfirmationRequestDto {
    private int pleaserServicePriorityConfirmationConfirm;
    private int pleaserServicePriorityConfirmationConfirmationLevel;
    private String pleaserServicePriorityConfirmationDescription;


    public TblPleaserServicePriorityConfirmation toConfirmation(String priorityId) {
        TblPleaserServicePriorityConfirmation confirmation = new TblPleaserServicePriorityConfirmation();
        confirmation.setPleaserServicePriorityConfirmationConfirm(pleaserServicePriorityConfirmationConfirm);
        confirmation.setPleaserServicePriorityConfirmationConfirmationLevel(pleaserServicePriorityConfirmationConfirmationLevel);
        confirmation.setPleaserServicePriorityConfirmationDescription(pleaserServicePriorityConfirmationDescription);
        confirmation.setPleaserServicePriorityConfirmationPleaserServicePriorityId(priorityId);
        return confirmation;
    }

}
