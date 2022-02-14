package com.velocia.scheduleapi.data.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UpdateTaskResponse(
        Long uuid,
        @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date,
        Integer period,
        String message) {
}
