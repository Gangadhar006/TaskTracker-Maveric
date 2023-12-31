package com.maveric.tasktracker.repository;

import com.maveric.tasktracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> findByEmployeeId(Long id);
}