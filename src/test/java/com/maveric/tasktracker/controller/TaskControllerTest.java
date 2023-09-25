package com.maveric.tasktracker.controller;

import com.maveric.tasktracker.payload.request.TaskRequest;
import com.maveric.tasktracker.payload.response.TaskResponse;
import com.maveric.tasktracker.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;
    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskController = new TaskController(taskService);
    }

    @Test
    void createTaskTest() {
        Long empId = 1L;
        TaskRequest taskRequest = new TaskRequest();
        TaskResponse taskResponse = new TaskResponse();
        when(taskService.createTask(taskRequest, empId)).thenReturn(taskResponse);

        ResponseEntity<TaskResponse> responseEntity = taskController.createTask(taskRequest, empId);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(taskResponse, responseEntity.getBody());
        verify(taskService, times(1)).createTask(taskRequest, empId);
    }

    @Test
    void fetchTasksTest() {
        Long empId = 1L;
        List<TaskResponse> taskList = new ArrayList<>();
        when(taskService.fetchTasks(empId)).thenReturn(taskList);

        ResponseEntity<List<TaskResponse>> responseEntity = taskController.fetchEmployees(empId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskList, responseEntity.getBody());

        verify(taskService, times(1)).fetchTasks(empId);
    }
}
