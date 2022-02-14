package com.velocia.scheduleapi.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
@Getter @Setter
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid")
    private Long UUID;
    @Column(name = "account_uuid")
    private Long accountUUID;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_uuid")
    private List<TaskEntity> tasks = new ArrayList<>();

    public ScheduleEntity(Long accountUUID, ArrayList<TaskEntity> tasks) {
        this(0L, accountUUID, tasks);
    }
}
