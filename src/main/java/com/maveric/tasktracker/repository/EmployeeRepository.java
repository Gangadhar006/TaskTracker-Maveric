package com.maveric.tasktracker.repository;

import com.maveric.tasktracker.entity.Employee;
import com.maveric.tasktracker.payload.response.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
