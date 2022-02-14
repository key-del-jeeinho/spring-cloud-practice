package com.velocia.scheduleapi;

import com.velocia.scheduleapi.data.dto.TaskDto;
import com.velocia.scheduleapi.data.entity.TaskEntity;
import com.velocia.scheduleapi.data.type.PeriodType;
import com.velocia.scheduleapi.repository.ScheduleRepository;
import com.velocia.scheduleapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.velocia.scheduleapi.util.DateUtil.getFirstDayOfMonth;
import static com.velocia.scheduleapi.util.DateUtil.getLastDayOfMonth;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final TaskRepository taskRepository;

    @Override @Transactional
    public TaskDto addTask(Long accountUUID, LocalDate date, String message, Integer period) {
        return taskRepository.save(new TaskEntity(
                date, PeriodType.of(period), message,
                scheduleRepository.getOrCreateScheduleByAccountUUID(accountUUID).getUUID())
        ).toDto();
    }

    @Override @Transactional
    public List<TaskDto> getMonthlySchedule(Long accountUUID, Integer year, Integer month, Pageable pageable) {
        return getScheduleByAccountUUIDAndDateBetween(
                accountUUID,
                getFirstDayOfMonth(year, month),
                getLastDayOfMonth(year, month),
                pageable
        );
    }

    @Override @Transactional
    public List<TaskDto> getDailySchedule(Long accountUUID, Integer year, Integer month, Integer day, Pageable pageable) {
        return toTaskDtoList(
                taskRepository.getTaskEntitiesByScheduleUUIDAndDate(
                        scheduleRepository.getOrCreateScheduleByAccountUUID(accountUUID).getUUID(),
                        LocalDate.of(year, month, day), pageable
        ));
    }

    @Override
    public void removeTask(Long uuid) {
        taskRepository.removeByUUID(uuid);
    }

    @Override @Transactional
    public TaskDto updateTask(Long uuid, String message, LocalDate date, Integer period) {
        return updateTask(taskRepository.getByUUID(uuid), message, date, period).toDto();
    }

    private TaskEntity updateTask(TaskEntity entity, String message, LocalDate date, Integer period) {
        return taskRepository.save(new TaskEntity(
                entity.getUUID(),
                date, PeriodType.of(period),
                message,
                entity.getScheduleUUID()
        ));
    }

    private List<TaskDto> getScheduleByAccountUUIDAndDateBetween(Long accountUUID, LocalDate rangeStart, LocalDate rangeEnd, Pageable pageable) {
        return toTaskDtoList(
                taskRepository.getTaskEntitiesByScheduleUUIDAndDateBetween(
                        scheduleRepository.getOrCreateScheduleByAccountUUID(accountUUID).getUUID(),
                        rangeStart, rangeEnd, pageable
        ));
    }

    private List<TaskDto> toTaskDtoList(Iterable<TaskEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(TaskEntity::toDto).toList();
    }
}
