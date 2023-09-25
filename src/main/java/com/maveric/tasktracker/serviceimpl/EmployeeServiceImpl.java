package com.maveric.tasktracker.serviceimpl;

import com.maveric.tasktracker.constants.ExceptionConstants;
import com.maveric.tasktracker.entity.Employee;
import com.maveric.tasktracker.exception.EmployeeNotFoundException;
import com.maveric.tasktracker.payload.request.EmployeeRequest;
import com.maveric.tasktracker.payload.response.EmployeeResponse;
import com.maveric.tasktracker.repository.EmployeeRepository;
import com.maveric.tasktracker.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepo;
    private ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo, ModelMapper mapper) {
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = mapper.map(employeeRequest, Employee.class);
        employee = employeeRepo.save(employee);
        return mapper.map(employee, EmployeeResponse.class);
    }

    @Override
    public List<EmployeeResponse> fetchEmployees() {
        List<Employee> employeeList = employeeRepo.findAll();
        return employeeList.stream().map(employee -> mapper.map(employee, EmployeeResponse.class)).toList();
    }
}