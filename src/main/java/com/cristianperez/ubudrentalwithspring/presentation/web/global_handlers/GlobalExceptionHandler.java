package com.cristianperez.ubudrentalwithspring.presentation.web.global_handlers;

import com.cristianperez.ubudrentalwithspring.logic.exceptions.InvalidTokenException;
import com.cristianperez.ubudrentalwithspring.presentation.web.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidTokenException.class})
    public final ResponseEntity<ApiError> handleException(Exception exception, WebRequest webRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (exception instanceof InvalidTokenException) {
            HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
            InvalidTokenException invalidTokenException = (InvalidTokenException) exception;
            return handleInvalidTokenException(invalidTokenException, httpHeaders, httpStatus, webRequest);
        }
        else {
            HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleInternalException(exception, null, httpHeaders, httpStatus, webRequest);
        }
    }

    private ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException invalidTokenException,
                                                                 HttpHeaders httpHeaders, HttpStatus httpStatus,
                                                                 WebRequest webRequest) {
        List<String> errorMessages = Collections.singletonList(invalidTokenException.getMessage());
        return handleInternalException(invalidTokenException, new ApiError(errorMessages), httpHeaders, httpStatus,
                webRequest);
    }

    private ResponseEntity<ApiError> handleInternalException(Exception exception, ApiError apiErrorBody,
                                                             HttpHeaders httpHeaders, HttpStatus httpStatus,
                                                             WebRequest webRequest) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpStatus)) {
            webRequest.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE,exception,WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(apiErrorBody,httpHeaders,httpStatus);
    }
}
