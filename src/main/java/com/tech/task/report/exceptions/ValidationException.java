package com.tech.task.report.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends AbstractCustomException{
    public ValidationException(ErrorDto error) {
        super(error);
    }
}
