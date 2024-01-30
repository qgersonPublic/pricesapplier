package com.between.test.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ResourceNotFound extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private final HttpStatus code;
    private final String message;

    public ResourceNotFound(String message) {
        super(message);
        this.code = HttpStatus.NOT_FOUND;
        this.message = message;
    }
}
