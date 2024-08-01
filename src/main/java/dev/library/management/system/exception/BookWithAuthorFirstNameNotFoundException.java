package dev.library.management.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookWithAuthorFirstNameNotFoundException extends RuntimeException{

    public BookWithAuthorFirstNameNotFoundException(final String authorFirstName) {
        super(String.format("Book with authorFirstName = %s not found", authorFirstName));
    }
}
