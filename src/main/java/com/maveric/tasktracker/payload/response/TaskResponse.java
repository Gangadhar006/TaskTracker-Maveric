package com.maveric.tasktracker.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private long id;
    private String taskName;
    private String status;
    private LocalDate time;
}