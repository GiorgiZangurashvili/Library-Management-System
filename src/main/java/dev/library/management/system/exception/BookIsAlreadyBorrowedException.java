package dev.library.management.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookIsAlreadyBorrowedException extends RuntimeException {

    public BookIsAlreadyBorrowedException(long id) {
        super(String.format("Book with id = %d is already borrowed", id));
    }
}

