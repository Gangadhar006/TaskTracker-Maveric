package com.maveric.tasktracker.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate hireDate;
    private String department;
}