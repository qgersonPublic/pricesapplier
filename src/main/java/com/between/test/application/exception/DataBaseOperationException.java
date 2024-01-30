package com.between.test.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class DataBaseOperationException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final HttpStatus code;
    private final String message;

    public DataBaseOperationException(String message) {
        super(message);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }
}
