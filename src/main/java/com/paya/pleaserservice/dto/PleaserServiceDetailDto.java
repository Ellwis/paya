package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserService;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import com.paya.pleaserservice.entity.TblPleaserServicePriorityConfirmation;
import com.paya.pleaserservice.entity.TblPriorityCondition;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PleaserServiceDetailDto {
    private String pleaserServiceId;
    private String pleaserServiceServiceNumber;
    private LocalDateTime pleaserServiceCreatedAt;
    private ServiceTypeDto pleaserServiceType;
    private String pleaserServiceTitle;
    private int pleaserServiceCount;
    private String pleaserServicePersonnelId;
    private List<PleaserServiceFieldDto> pleaserServiceFields;
    private List<AttachmentDto> attachments;
    private List<PriorityDto> priorities;


    public PleaserServiceDetailDto(
            TblPleaserService service,
            List<AttachmentDto> attachments
    ) {
        this.pleaserServiceId = service.getPleaserServiceId();
        this.pleaserServiceServiceNumber = service.getPleaserServiceServiceNumber();
        this.pleaserServiceCreatedAt = service.getPleaserServiceCreatedAt();
        this.pleaserServiceType = service.getPleaserServiceType();
        this.pleaserServiceTitle = service.getPleaserServiceTitle();
        this.pleaserServiceCount = service.getPleaserServiceCount();
        this.pleaserServicePersonnelId = service.getPleaserServicePersonnelId();
        this.attachments = attachments;
    }


    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Data
    public static class PriorityDto {
        private String pleaserServicePriorityId;

        private int pleaserServicePriorityNumber;

        private String pleaserServicePriorityPleaserServiceId;

        private int pleaserServicePriorityWeight;

        private BigDecimal pleaserServicePriorityAmount;

        private int pleaserServicePriorityAllowedCount;

        private String pleaserServicePriorityMeasurementUnit;

        private LocalDate pleaserServicePriorityStartDate;

        private LocalDate pleaserServicePriorityEndDate;

        private String pleaserServicePriorityDescription;

        private AdminPleaserPaymentMethodDto paymentMethod;

        private boolean pleaserServicePriorityCancelOption;

        private LocalDateTime pleaserServicePriorityMaximumCancellationDate;

        private Long includedPersonnelNumber;

        private List<TblPriorityCondition> conditions;

        private List<AttachmentDto> attachments;

        private TblPleaserServicePriorityConfirmation confirmation;


        public PriorityDto(TblPleaserServicePriority priority, AdminPleaserPaymentMethodDto paymentMethod, List<TblPriorityCondition> conditions, List<AttachmentDto> attachments, TblPleaserServicePriorityConfirmation confirmation) {
            this.pleaserServicePriorityId = priority.getPleaserServicePriorityId();
            this.pleaserServicePriorityNumber = priority.getPleaserServicePriorityNumber();
            this.pleaserServicePriorityWeight = priority.getPleaserServicePriorityWeight();
            this.pleaserServicePriorityAmount = priority.getPleaserServicePriorityAmount();
            this.pleaserServicePriorityAllowedCount = priority.getPleaserServicePriorityAllowedCount();
            this.pleaserServicePriorityMeasurementUnit = priority.getPleaserServicePriorityMeasurementUnit();
            this.pleaserServicePriorityStartDate = priority.getPleaserServicePriorityStartDate();
            this.pleaserServicePriorityEndDate = priority.getPleaserServicePriorityEndDate();
            this.pleaserServicePriorityDescription = priority.getPleaserServicePriorityDescription();
            this.paymentMethod = paymentMethod;
            this.pleaserServicePriorityCancelOption = priority.getPleaserServicePriorityCancelOption();
            this.pleaserServicePriorityMaximumCancellationDate = priority.getPleaserServicePriorityMaximumCancellationDate();
            this.includedPersonnelNumber = 100L;
            this.conditions = conditions;
            this.attachments = attachments;
            this.confirmation = confirmation;
        }

    }

}
