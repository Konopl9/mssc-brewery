package mishcma.springframework.msscbrewery.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public List<String> validationErrorHandler(ConstraintViolationException exception) {
        List<String> errors = new ArrayList<>(exception.getConstraintViolations().size());

        exception.getConstraintViolations().forEach(constraintValidation -> {
            errors.add(constraintValidation.getPropertyPath() + " : " + constraintValidation.getMessage());
        });

        return errors;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> handleBindException(BindException exception) {
        return new ResponseEntity<>(exception.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
}
