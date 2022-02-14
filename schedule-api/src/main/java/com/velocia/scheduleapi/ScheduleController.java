package com.velocia.scheduleapi;

import com.velocia.scheduleapi.data.dto.TaskDto;
import com.velocia.scheduleapi.data.request.AddScheduleRequest;
import com.velocia.scheduleapi.data.request.UpdateTaskRequest;
import com.velocia.scheduleapi.data.response.AddAccountResponse;
import com.velocia.scheduleapi.data.response.GetDailyScheduleResponse;
import com.velocia.scheduleapi.data.response.GetMonthlyScheduleResponse;
import com.velocia.scheduleapi.data.response.UpdateTaskResponse;
import com.velocia.scheduleapi.data.type.PeriodType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/{accountUUID}")
    public ResponseEntity<AddAccountResponse> addTaskByPeriod(@PathVariable Long accountUUID,
                                                              @RequestBody AddScheduleRequest request) {
        TaskDto task = scheduleService.addTask(accountUUID, request.date(), request.message(),
                request.period() == null ? PeriodType.DAY.getId() : request.period());
        AddAccountResponse response = new AddAccountResponse(task.UUID(), task.date(), task.period().getId(), task.message());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountUUID}/monthly/{year}/{month}")
    public ResponseEntity<GetMonthlyScheduleResponse> getMonthlySchedule(@PathVariable Long accountUUID,
                                                                         @PathVariable Integer year,
                                                                         @PathVariable Integer month,
                                                                         Pageable pageable) {
        List<TaskDto> tasks = scheduleService.getMonthlySchedule(accountUUID, year, month, pageable);
        GetMonthlyScheduleResponse response = new GetMonthlyScheduleResponse(year, month, pageable.getPageSize(), pageable.getPageNumber(), tasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountUUID}/daily/{year}/{month}/{day}")
    public ResponseEntity<GetDailyScheduleResponse> getDailySchedule(@PathVariable Long accountUUID,
                                                                     @PathVariable Integer year,
                                                                     @PathVariable Integer month,
                                                                     @PathVariable Integer day,
                                                                     Pageable pageable) {
        List<TaskDto> tasks = scheduleService.getDailySchedule(accountUUID, year, month, day, pageable);
        GetDailyScheduleResponse response = new GetDailyScheduleResponse(year, month, day, pageable.getPageSize(), pageable.getPageNumber(), tasks);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> removeTask(@PathVariable Long uuid) {
        scheduleService.removeTask(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable Long uuid, @RequestBody UpdateTaskRequest request) {
        TaskDto task = scheduleService.updateTask(uuid, request.message(), request.date(), request.period());
        UpdateTaskResponse response = new UpdateTaskResponse(task.UUID(), task.date(), task.period().getId(), task.message());
        return ResponseEntity.ok(response);
    }
}
