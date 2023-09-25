package com.maveric.tasktracker.controller;

import com.maveric.tasktracker.payload.request.TaskRequest;
import com.maveric.tasktracker.payload.response.TaskResponse;
import com.maveric.tasktracker.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.maveric.tasktracker.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(value = TASK_BASE_PATH)
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = EMPLOYEE_ID_PATH)
    public ResponseEntity<TaskResponse> createTask(
            @RequestBody @Valid TaskRequest taskRequest,
            @PathVariable Long empId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskRequest, empId));
    }

    @GetMapping(value = EMPLOYEE_ID_PATH)
    public ResponseEntity<List<TaskResponse>> fetchEmployees(@PathVariable Long empId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.fetchTasks(empId));
    }
}
