package dev.library.management.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookWithAuthorIdNotFoundException extends RuntimeException{

    public BookWithAuthorIdNotFoundException(final long authorId) {
        super(String.format("Book with authorId = %d not found", authorId));
    }
}
