package dev.library.management.system.exception.notfound;

public class BookWithTitleNotFoundException extends NotFoundException {

    public BookWithTitleNotFoundException(final String title) {
        super(String.format("Book with title = '%s' not found", title));
    }

}
