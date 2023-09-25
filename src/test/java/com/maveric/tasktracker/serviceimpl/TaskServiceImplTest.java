package com.maveric.tasktracker.serviceimpl;

import com.maveric.tasktracker.entity.Employee;
import com.maveric.tasktracker.entity.Task;
import com.maveric.tasktracker.exception.EmployeeNotFoundException;
import com.maveric.tasktracker.exception.TaskNotFoundException;
import com.maveric.tasktracker.payload.request.TaskRequest;
import com.maveric.tasktracker.payload.response.TaskResponse;
import com.maveric.tasktracker.repository.EmployeeRepository;
import com.maveric.tasktracker.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepo;

    @Mock
    private EmployeeRepository employeeRepo;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTaskTest() {
        Long empId = 1L;
        Employee employee = new Employee();
        employee.setId(empId);

        TaskRequest taskRequest = new TaskRequest();
        Task task = new Task();
        TaskResponse taskResponse = new TaskResponse();

        when(employeeRepo.findById(empId)).thenReturn(Optional.of(employee));

        when(mapper.map(taskRequest, Task.class)).thenReturn(task);
        when(taskRepo.save(task)).thenReturn(task);
        when(mapper.map(task, TaskResponse.class)).thenReturn(taskResponse);

        TaskResponse result = taskService.createTask(taskRequest, empId);

        verify(employeeRepo).findById(empId);
        verify(taskRepo).save(task);
        verify(mapper).map(taskRequest, Task.class);
        verify(mapper).map(task, TaskResponse.class);

        assertEquals(taskResponse, result);
    }


    @Test
    void throwsEmployeeNotFoundException_EmployeeNotExistsTest() {
        Long empId = 1L;
        TaskRequest taskRequest = new TaskRequest();

        when(employeeRepo.findById(empId)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> taskService.createTask(taskRequest, empId));
    }

    @Test
    void testFetchTasks() {
        Long empId = 1L;
        Employee employee = new Employee();

        Task task1 = new Task();
        task1.setEmployee(employee);
        Task task2 = new Task();
        task2.setEmployee(employee);

        List<Task> taskList = Arrays.asList(task1, task2);

        TaskResponse expectedResponse = new TaskResponse();

        when(employeeRepo.findById(empId)).thenReturn(Optional.of(employee));
        when(taskRepo.findByEmployeeId(empId)).thenReturn(Optional.of(taskList));
        when(mapper.map(any(), eq(TaskResponse.class))).thenReturn(expectedResponse);

        List<TaskResponse> actualResponse = taskService.fetchTasks(empId);

        assertEquals(2, actualResponse.size());
        assertEquals(expectedResponse, actualResponse.get(0));

        verify(employeeRepo, times(1)).findById(empId);
        verify(taskRepo, times(1)).findByEmployeeId(empId);
    }

    @Test
    void throwsTaskNotFoundException_NoTasksForEmployeeTest() {
        Long empId = 1L;
        Employee employee = new Employee();

        when(employeeRepo.findById(empId)).thenReturn(Optional.of(employee));
        when(taskRepo.findByEmployeeId(empId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.fetchTasks(empId));
    }
}
