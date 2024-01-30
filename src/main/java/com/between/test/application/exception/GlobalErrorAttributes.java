package com.between.test.application.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private final List<ExceptionCustomizedClass> exceptionsFiles = List.of(
             new ExceptionCustomizedClass(ResourceNotFound.class, HttpStatus.NOT_FOUND)
    );

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        Throwable error = getError(request);

        final String errorDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        Optional<ExceptionCustomizedClass> exceptionRuleOptional = exceptionsFiles.stream()
                .map(rule -> rule.clazz().isInstance(error) ? rule : null)
                .filter(Objects::nonNull)
                .findFirst();

        return exceptionRuleOptional
                .<Map<String, Object>>map(
                        exceptionRule -> Map.of(
                                "code", exceptionRule.status().value(),
                                "message", error.getMessage(),
                                "errorTime", errorDateTime))
                .orElseGet(() -> Map.of(
                        "code", getUncheckedHttpStatus(error).value(),
                        "message", error.getMessage(),
                        "errorTime", errorDateTime)
                );
    }

    /**
     *  Checks the customized error kind of thrown and retrieves the proper HttpStatus code
     *
     * @param error The customized error type
     * @return the proper {@link HttpStatus} thrown
     */
    private HttpStatusCode getUncheckedHttpStatus(Throwable error) {
        return error instanceof ResponseStatusException err
                ? err.getStatusCode():
                MergedAnnotations.from(
                                error.getClass(),
                                MergedAnnotations.SearchStrategy.TYPE_HIERARCHY
                        ).get(ResponseStatus.class)
                        .getValue("code", HttpStatus.class)
                        .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
