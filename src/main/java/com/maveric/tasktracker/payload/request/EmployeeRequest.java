package com.maveric.tasktracker.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

import static com.maveric.tasktracker.constants.ValidationConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {
    @NotBlank(message = EMPTY_NAME_ERROR_MESSAGE)
    private String name;
    @NotBlank(message = EMPTY_EMAIL_ERROR_MESSAGE)
    @Pattern(regexp = EMAIL_PATTERN, message = EMAIL_PATTERN_ERROR_MESSAGE)
    private String email;

    @Column(length = PHONE_LENGTH)
    @Size(min = PHONE_LENGTH, max = PHONE_LENGTH, message = INVALID_PHONE_LENGTH_MESSAGE)
    private String phone;

    @PastOrPresent(message = INVALID_DATE_MESSAGE)
    private LocalDate hireDate;
    @NotBlank(message = EMPTY_DEPARTMENT_ERROR_MESSAGE)
    private String department;
}