package com.velocia.scheduleapi.data.response;

import com.velocia.scheduleapi.data.dto.TaskDto;

import java.util.List;

public record GetDailyScheduleResponse(
        Integer year,
        Integer month,
        Integer day,
        Integer pageSize,
        Integer pageNum,
        List<TaskDto> tasks
) {}
