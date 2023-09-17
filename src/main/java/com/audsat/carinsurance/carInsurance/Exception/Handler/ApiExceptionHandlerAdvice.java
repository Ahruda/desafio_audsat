package com.audsat.carinsurance.carInsurance.Exception.Handler;

import com.audsat.carinsurance.carInsurance.Exception.ForeignEntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandlerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<?> notFoundException() {
        return buildResponse(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }

    @ExceptionHandler(ForeignEntityNotFoundException.class)
    ResponseEntity<?> foreignNotFoundException(Exception exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
    }

    private ResponseEntity<?> buildResponse(final HttpStatus statusCode, final String cause) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", statusCode.value());
        body.put("error", statusCode.getReasonPhrase());
        body.put("message", cause);

        return ResponseEntity.status(statusCode).body(body);
    }
}
