package com.paya.pleaserservice.utils;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DataConversion {

    public static  LocalDateTime convertStringToLocalDateTime(String dateTime) {
        if (dateTime == null || dateTime.isBlank())
            return null;
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
            return localDateTime;
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "date time parsing error : " + dateTime);
        }
    }

}
