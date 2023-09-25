package com.maveric.tasktracker.service;

import com.maveric.tasktracker.payload.request.EmployeeRequest;
import com.maveric.tasktracker.payload.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    List<EmployeeResponse> fetchEmployees();
}