package com.cristianperez.ubudrentalwithspring.logic.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
