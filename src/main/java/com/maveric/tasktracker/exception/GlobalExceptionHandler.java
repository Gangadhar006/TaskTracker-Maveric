package com.maveric.tasktracker.exception;

import com.maveric.tasktracker.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        List<ObjectError> listOfErrors = exception.getBindingResult().getAllErrors();

        String errorslist = "";

        for (ObjectError list : listOfErrors)
            errorslist = errorslist + list.getDefaultMessage() + ",";

        exception.getBindingResult().getAllErrors().forEach((err) -> {
            String errorMessage = err.getDefaultMessage();
            errorResponse.setStatus(HttpStatus.BAD_REQUEST);
            errorResponse.setMessage(exception.getFieldError().getField() + " " + errorMessage);
        });

        errorResponse.setMessage(errorslist);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> employeeNotFound(EmployeeNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> taskNotFound(TaskNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
