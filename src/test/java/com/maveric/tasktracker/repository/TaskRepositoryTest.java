package com.maveric.tasktracker.repository;

import com.maveric.tasktracker.entity.Employee;
import com.maveric.tasktracker.entity.Task;
import com.maveric.tasktracker.enums.DepartmentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TaskRepositoryTest {

    @Mock
    private TaskRepository taskRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByEmployeeIdTest() {
        Employee employee = new Employee(1l, "emp1", "emp1@email.com", "1231231231", LocalDate.of(2023, 03, 10), DepartmentType.QA);
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task 1", employee, "In Progress",LocalDate.now()));
        tasks.add(new Task(2L, "Task 2", employee, "In Progress",LocalDate.now()));

        when(taskRepo.findByEmployeeId(employee.getId())).thenReturn(Optional.of(tasks));

        Optional<List<Task>> result = taskRepo.findByEmployeeId(employee.getId());

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertEquals("Task 1", result.get().get(0).getTaskName());
        assertEquals("Task 2", result.get().get(1).getTaskName());

        verify(taskRepo, times(1)).findByEmployeeId(employee.getId());
    }
}
