package com.maveric.tasktracker.serviceimpl;

import com.maveric.tasktracker.entity.Employee;
import com.maveric.tasktracker.payload.request.EmployeeRequest;
import com.maveric.tasktracker.payload.response.EmployeeResponse;
import com.maveric.tasktracker.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEmployeeTest() {
        EmployeeRequest request = new EmployeeRequest();
        Employee employee = new Employee();
        EmployeeResponse response = new EmployeeResponse();

        when(modelMapper.map(request, Employee.class)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(modelMapper.map(employee, EmployeeResponse.class)).thenReturn(response);

        EmployeeResponse result = employeeService.createEmployee(request);

        assertEquals(response, result);
        verify(modelMapper, times(1)).map(request, Employee.class);
        verify(employeeRepository, times(1)).save(employee);
        verify(modelMapper, times(1)).map(employee, EmployeeResponse.class);
    }

    @Test
    void fetchEmployeesTest() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());
        employeeList.add(new Employee());

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeResponse> result = employeeService.fetchEmployees();

        assertEquals(employeeList.size(), result.size());
        verify(employeeRepository, times(1)).findAll();
    }
}
