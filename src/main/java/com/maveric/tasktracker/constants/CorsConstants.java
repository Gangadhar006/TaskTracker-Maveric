package com.maveric.tasktracker.constants;

public class CorsConstants {
    public static final String API_MAPPING = "/api/**";
    public static final String ALLOWED_ORIGIN = "http://localhost:4200";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE"};
    public static final String ALLOWED_HEADERS = "*";
    public static final boolean ALLOW_CREDENTIALS = true;
}