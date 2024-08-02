package dev.library.management.system.exception.notfound;

public class BookWithAuthorFirstNameNotFoundException extends NotFoundException{

    public BookWithAuthorFirstNameNotFoundException(final String authorFirstName) {
        super(String.format("Book with authorFirstName = %s not found", authorFirstName));
    }
}
