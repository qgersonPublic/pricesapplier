package com.between.test.application.exception;

import org.springframework.http.HttpStatus;

public record ExceptionCustomizedClass(Class<?> clazz, HttpStatus status) {
}
