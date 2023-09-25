package com.maveric.tasktracker.controller;

import com.maveric.tasktracker.service.EmployeeService;
import com.maveric.tasktracker.payload.request.EmployeeRequest;
import com.maveric.tasktracker.payload.response.EmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.maveric.tasktracker.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(value = EMPLOYEE_BASE_PATH)
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeRequest));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> fetchEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.fetchEmployees());
    }
}