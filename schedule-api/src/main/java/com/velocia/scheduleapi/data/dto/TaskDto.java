package com.velocia.scheduleapi.data.dto;

import com.velocia.scheduleapi.data.type.PeriodType;

import java.time.LocalDate;

public record TaskDto(
        Long UUID,
        Long scheduleUUID,
        LocalDate date,
        PeriodType period,
        String message
) {
}
