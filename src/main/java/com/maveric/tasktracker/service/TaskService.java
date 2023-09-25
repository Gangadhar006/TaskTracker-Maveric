package com.maveric.tasktracker.service;

import com.maveric.tasktracker.payload.request.TaskRequest;
import com.maveric.tasktracker.payload.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest, Long empId);

    List<TaskResponse> fetchTasks(Long empId);
}