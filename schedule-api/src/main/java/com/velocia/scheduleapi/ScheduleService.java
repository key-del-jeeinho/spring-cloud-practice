package com.velocia.scheduleapi;

import com.velocia.scheduleapi.data.dto.TaskDto;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

//해당 Service 는 DB 접근이나 Repository 로만 구현가능하지 않다. 그러므로 Transactional 이 강제되지 않는다.
public interface ScheduleService {
    TaskDto addTask(Long accountUUID, LocalDate date, String message, Integer period);

    List<TaskDto> getMonthlySchedule(Long accountUUID, Integer year, Integer month, Pageable pageable);

    List<TaskDto> getDailySchedule(Long accountUUID, Integer year, Integer month, Integer day, Pageable pageable);

    void removeTask(Long uuid);

    TaskDto updateTask(Long uuid, String message, LocalDate date, Integer period);
}
