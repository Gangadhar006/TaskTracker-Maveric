package com.maveric.tasktracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maveric.tasktracker.enums.DepartmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq_generator")
    @SequenceGenerator(name = "employee_seq_generator", sequenceName = "employee_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phone;
    private LocalDate hireDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DepartmentType department;
}