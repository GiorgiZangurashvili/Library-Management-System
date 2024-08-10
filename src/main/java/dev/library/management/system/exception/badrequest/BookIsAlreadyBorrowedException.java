package dev.library.management.system.exception.badrequest;

import dev.library.management.system.exception.notfound.NotFoundException;

public class BookIsAlreadyBorrowedException extends NotFoundException {

    public BookIsAlreadyBorrowedException(final long id) {
        super(String.format("Book with id = %d is already borrowed", id));
    }
}

