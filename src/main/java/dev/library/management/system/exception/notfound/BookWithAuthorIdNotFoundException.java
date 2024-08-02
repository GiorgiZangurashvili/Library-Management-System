package dev.library.management.system.exception.notfound;

public class BookWithAuthorIdNotFoundException extends NotFoundException{

    public BookWithAuthorIdNotFoundException(final long authorId) {
        super(String.format("Book with authorId = %d not found", authorId));
    }
}
