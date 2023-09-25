package com.maveric.tasktracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

import static com.maveric.tasktracker.constants.AppConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq_generator")
    @SequenceGenerator(name = "task_seq_generator", sequenceName = "task_seq", allocationSize = 1)
    private Long id;
    private String taskName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
    private String status;
    @CreationTimestamp
    private LocalDate time;

    @PrePersist
    public void setStatus() {
        status = TASK_STATUS_MESSAGE;
    }
}