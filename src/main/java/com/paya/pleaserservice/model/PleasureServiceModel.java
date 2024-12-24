package com.paya.pleaserservice.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class PleasureServiceModel {
    private String pleaserServiceId;
    private String pleaserServiceServiceNumber;
    private LocalDateTime pleaserServiceCreatedAt;
    private int pleaserServiceType;
    private Boolean pleaserServiceIsDraft = true;
    private String pleaserServiceTitle;
    private int pleaserServiceCount;
    private String pleaserServicePersonnelId;


}
