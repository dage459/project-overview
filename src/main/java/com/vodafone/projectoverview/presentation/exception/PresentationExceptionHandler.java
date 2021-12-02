package com.vodafone.projectoverview.presentation.exception;

import com.vodafone.projectoverview.ProjectOverviewConfiguration;
import com.vodafone.projectoverview.exeption.RequestValidationException;
import com.vodafone.projectoverview.exeption.ResourceConflictException;
import com.vodafone.projectoverview.exeption.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PresentationExceptionHandler {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(ProjectOverviewConfiguration.LOGGER_NAME);

    public PresentationExceptionHandler() {
        super();
    }

    @ExceptionHandler(RequestValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationFailure(final RuntimeException ex) {
        LOGGER.warn(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFound(final RuntimeException ex) {
        LOGGER.warn(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(ResourceConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleResourceConflict(final RuntimeException ex) {
        LOGGER.warn(ex.getMessage());
        return ex.getMessage();
    }
}
