package com.maveric.tasktracker.constants;

public class AppConstants {
    public static final String LOGGING_POINTCUT_PATH = "execution(* org.maveric.tasktracker.serviceimpl.*.*(..))";
    public static final String LOGGING_POINTCUT_NAME = "logging()";
    public static final String LOGGING_BEFORE = "Entering method: '{}' class: '{}'";
    public static final String LOGGING_AFTER = "Exiting method: '{}' class: '{}'";

    public static final String API_TITLE = "tasktracker API";
    public static final String API_VERSION = "1.0";
    public static final String API_DESCRIPTION = "Admin can track the tasks assigned to employees";

    public static final String TASK_STATUS_MESSAGE="In Progress";

}
