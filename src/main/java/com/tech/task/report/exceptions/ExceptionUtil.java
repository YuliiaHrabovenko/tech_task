package com.tech.task.report.exceptions;

public abstract class ExceptionUtil {
    public static final ErrorDto EXPIRED_TOKEN_EXCEPTION = new ErrorDto(401, "Expired jwt token");
    public static final ErrorDto INVALID_TOKEN_EXCEPTION = new ErrorDto(401, "Invalid jwt token");
    public static final ErrorDto AUTH_HEADER_EXCEPTION = new ErrorDto(401, "Empty `Authorization` header");
    public static final ErrorDto USER_NOT_FOUND_EXCEPTION = new ErrorDto(401, "User not found");
    public static final ErrorDto INVALID_CREDENTIALS_EXCEPTION = new ErrorDto(401, "Invalid credentials");
    public static final ErrorDto USER_ALREADY_EXISTS_EXCEPTION = new ErrorDto(400, "User with this email is already registered");
    public static final ErrorDto ACCESS_DENIED_EXCEPTION = new ErrorDto(403, "Access denied");
}
