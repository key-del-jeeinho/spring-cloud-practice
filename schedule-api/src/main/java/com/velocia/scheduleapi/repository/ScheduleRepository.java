package com.velocia.scheduleapi.repository;

import com.velocia.scheduleapi.data.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    //해당 유저의 일정표를 가져온다 (만약 일정표가 없을 경우 새로 생성하여 가져온다)
    default ScheduleEntity getOrCreateScheduleByAccountUUID(Long accountUUID) {
        if(existsByAccountUUID(accountUUID))
            return getByAccountUUID(accountUUID);
        else return save(new ScheduleEntity(accountUUID, new ArrayList<>()));
    }

    boolean existsByAccountUUID(Long accountUUID);

    ScheduleEntity getByAccountUUID(Long accountUUID);
}
