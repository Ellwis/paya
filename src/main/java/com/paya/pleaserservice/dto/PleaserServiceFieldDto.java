package com.paya.pleaserservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.paya.pleaserservice.entity.TblPleaserServiceField;
import com.paya.pleaserservice.entity.TblPleaserServiceFieldContent;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PleaserServiceFieldDto {
    private String pleaserServiceFieldId;
    private String PleaserServiceFieldPleaserServiceId;
    private String pleaserServiceFieldTitle;
    private List<FieldContent> contents;


    public PleaserServiceFieldDto(TblPleaserServiceField field, List<TblPleaserServiceFieldContent> contents) {
        this.pleaserServiceFieldId = field.getPleaserServiceFieldId();
        PleaserServiceFieldPleaserServiceId = field.getPleaserServiceFieldPleaserServiceId();
        this.pleaserServiceFieldTitle = field.getPleaserServiceFieldTitle();
        this.contents = contents.stream().map(FieldContent::new).toList();
    }


    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private class FieldContent {
        private String pleaserServiceFieldContentId;
        private String pleaserServiceFieldContentTitle;


        public FieldContent(TblPleaserServiceFieldContent content) {
            this.pleaserServiceFieldContentId = content.getPleaserServiceFieldContentId();
            this.pleaserServiceFieldContentTitle = content.getPleaserServiceFieldContentTitle();
        }

    }

}
