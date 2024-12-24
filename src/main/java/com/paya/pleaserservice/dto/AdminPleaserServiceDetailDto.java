package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AdminPleaserServiceDetailDto {
    private String pleaserServiceId;
    private String pleaserServiceServiceNumber;
    private LocalDateTime pleaserServiceCreatedAt;
    private int pleaserServiceType;
    private String pleaserServiceTitle;
    private int pleaserServiceCount;
    private List<PriorityDto> priorities;

    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private static class PriorityDto {
        private String pleaserServicePriorityId;
        private int pleaserServicePriorityStatus;
        public int pleaserServicePriorityNumber;


        public PriorityDto(TblPleaserServicePriority priority) {
            this.pleaserServicePriorityId = priority.getPleaserServicePriorityId();
            //TODO : Set Status
//            this.pleaserServicePriorityStatus = null;
            this.pleaserServicePriorityNumber = priority.getPleaserServicePriorityNumber();
        }

    }


    private List<PriorityDto> mapToPriorityDto(List<TblPleaserServicePriority> priorities) {
        return priorities
                .stream()
                .map(PriorityDto::new)
                .toList();
    }
}
