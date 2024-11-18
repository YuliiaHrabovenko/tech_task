package com.tech.task.report.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"stackTrace", "cause", "localizedMessage", "suppressed", "statusCode"})
public abstract class AbstractCustomException extends RuntimeException {
    private final Integer code;
    private final String message;

    public AbstractCustomException(ErrorDto error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }
}
