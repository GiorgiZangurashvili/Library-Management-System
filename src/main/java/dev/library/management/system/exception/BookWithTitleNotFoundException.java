package dev.library.management.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookWithTitleNotFoundException extends RuntimeException {

    public BookWithTitleNotFoundException(final String title) {
        super(String.format("Book with title = '%s' not found", title));
    }

}
