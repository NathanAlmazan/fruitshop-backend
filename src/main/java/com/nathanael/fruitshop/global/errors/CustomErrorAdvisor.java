package com.nathanael.fruitshop.global.errors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomErrorAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(
            InvalidRequestException ex, WebRequest request
    ) {
        CustomErrorResponse error = new CustomErrorResponse(400);
        error.addErrorMessage(ex.getMessage());
        return new ResponseEntity<>(error.getErrorObject(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request
    ) {
        CustomErrorResponse error = new CustomErrorResponse(404);
        error.addErrorMessage(ex.getMessage());
        return new ResponseEntity<>(error.getErrorObject(), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException ex,
            HttpHeaders headers,
            @NotNull HttpStatus status,
            WebRequest request
    ) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        CustomErrorResponse error = new CustomErrorResponse(status.value());
        error.setErrorMessage(errors);

        return new ResponseEntity<>(error.getErrorObject(), HttpStatus.BAD_REQUEST);
    }
}
