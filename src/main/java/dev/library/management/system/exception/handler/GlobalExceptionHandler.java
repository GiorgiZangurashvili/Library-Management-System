package dev.library.management.system.exception.handler;

import dev.library.management.system.domain.dto.response.ExceptionResponseDto;
import dev.library.management.system.exception.badrequest.BookIsAlreadyBorrowedException;
import dev.library.management.system.exception.notfound.NotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleEntityNotFoundException(
            final NotFoundException notFoundException
    ) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                notFoundException.getMessage()
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookIsAlreadyBorrowedException.class)
    public ResponseEntity<ExceptionResponseDto> handleBookIsAlreadyBorrowedException(
            final BookIsAlreadyBorrowedException bookIsAlreadyBorrowedException
    ) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                bookIsAlreadyBorrowedException.getMessage()
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            final MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException
                .getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationExceptions(
            final ConstraintViolationException constraintViolationException
    ) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = constraintViolationException
                .getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponseDto> handleDataIntegrityViolationException(
            final DataIntegrityViolationException dataIntegrityViolationException
    ) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT,
                dataIntegrityViolationException.getMessage()
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.CONFLICT);
    }

}
