package com.maveric.tasktracker.controller;

import com.maveric.tasktracker.payload.request.EmployeeRequest;
import com.maveric.tasktracker.payload.response.EmployeeResponse;
import com.maveric.tasktracker.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {
    @MockBean
    private ModelMapper mapper;
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createEmployeeTest() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        EmployeeResponse employeeResponse = new EmployeeResponse();

        when(employeeService.createEmployee(employeeRequest)).thenReturn(employeeResponse);

        ResponseEntity<EmployeeResponse> responseEntity = employeeController.createEmployee(employeeRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(employeeResponse, responseEntity.getBody());

        verify(employeeService, times(1)).createEmployee(employeeRequest);
    }

    @Test
    void fetchEmployeesTest() {
        EmployeeResponse employee1 = new EmployeeResponse();
        EmployeeResponse employee2 = new EmployeeResponse();
        List<EmployeeResponse> employees = Arrays.asList(employee1, employee2);

        when(employeeService.fetchEmployees()).thenReturn(employees);

        ResponseEntity<List<EmployeeResponse>> responseEntity = employeeController.fetchEmployees();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employees, responseEntity.getBody());

        verify(employeeService, times(1)).fetchEmployees();
    }
}