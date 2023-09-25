package com.maveric.tasktracker.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.maveric.tasktracker.constants.ValidationConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @NotBlank(message = EMPTY_TASK_ERROR_MESSAGE)
    private String taskName;
}