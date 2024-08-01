package dev.library.management.system.exception.handler;

import dev.library.management.system.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(final EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookWithTitleNotFoundException.class)
    public ResponseEntity<?> handleBookWithTitleNotFoundException(final BookWithTitleNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookWithAuthorIdNotFoundException.class)
    public ResponseEntity<?> handleBookWithAuthorIdNotFoundException(final BookWithAuthorIdNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookWithAuthorFirstNameNotFoundException.class)
    public ResponseEntity<?> handleBookWithAuthorFirstNameNotFoundException(final BookWithAuthorFirstNameNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookIsAlreadyBorrowedException.class)
    public ResponseEntity<?> handleBookIsAlreadyBorrowedException(final BookIsAlreadyBorrowedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
