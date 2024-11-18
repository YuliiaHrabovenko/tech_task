package com.tech.task.report.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private Integer code;
    private String message;

    public ErrorDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
