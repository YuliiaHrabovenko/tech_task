package com.tech.task.report.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthException extends AbstractCustomException {
    public AuthException(ErrorDto error) {
        super(error);
    }
}
