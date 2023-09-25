package com.maveric.tasktracker.constants;

public class ValidationConstants {
    public static final String EMPTY_NAME_ERROR_MESSAGE = "Name cannot be empty";
    public static final String EMPTY_EMAIL_ERROR_MESSAGE = "Email cannot be empty";
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String EMAIL_PATTERN_ERROR_MESSAGE = "Invalid email format";
    public static final int PHONE_LENGTH = 10;
    public static final String INVALID_PHONE_LENGTH_MESSAGE = "Phone number must be exactly 10 characters long";
    public static final String INVALID_DATE_MESSAGE ="Date hired must be in the past or present";
    public static final String EMPTY_DEPARTMENT_ERROR_MESSAGE="Department cannot be empty";
    public static final String EMPTY_TASK_ERROR_MESSAGE="Task Name cannot be empty";
}
