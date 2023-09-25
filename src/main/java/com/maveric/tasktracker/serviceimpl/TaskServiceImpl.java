package com.maveric.tasktracker.serviceimpl;

import com.maveric.tasktracker.constants.ExceptionConstants;
import com.maveric.tasktracker.entity.Employee;
import com.maveric.tasktracker.entity.Task;
import com.maveric.tasktracker.exception.EmployeeNotFoundException;
import com.maveric.tasktracker.exception.TaskNotFoundException;
import com.maveric.tasktracker.payload.request.TaskRequest;
import com.maveric.tasktracker.payload.response.TaskResponse;
import com.maveric.tasktracker.repository.EmployeeRepository;
import com.maveric.tasktracker.repository.TaskRepository;
import com.maveric.tasktracker.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepo;
    private EmployeeRepository employeeRepo;
    private ModelMapper mapper;

    public TaskServiceImpl(TaskRepository taskRepo, EmployeeRepository employeeRepo, ModelMapper mapper) {
        this.taskRepo = taskRepo;
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest taskRequest, Long empId) {
        Employee employee = verifyEmployee(empId);
        Task task = mapper.map(taskRequest, Task.class);
        task.setEmployee(employee);
        task = taskRepo.save(task);
        return mapper.map(task, TaskResponse.class);
    }

    private Employee verifyEmployee(Long empId) {
        return employeeRepo.findById(empId).orElseThrow(
                () -> new EmployeeNotFoundException(ExceptionConstants.EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE)
        );
    }

    @Override
    public List<TaskResponse> fetchTasks(Long empId) {
        verifyEmployee(empId);
        List<Task> taskList = taskRepo.findByEmployeeId(empId).orElseThrow(
                () -> new TaskNotFoundException(ExceptionConstants.TASK_NOT_FOUND_EXCEPTION_MESSAGE)
        );
        return taskList.stream().map(task -> mapper.map(task, TaskResponse.class)).toList();
    }
}