package com.velocia.scheduleapi.data.response;

import com.velocia.scheduleapi.data.dto.TaskDto;

import java.util.List;

public record GetWeeklyScheduleResponse(
        Integer year,
        Integer month,
        Integer week,
        Integer pageSize,
        Integer pageNum,
        List<TaskDto> tasks
) {}
