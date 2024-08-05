package dev.library.management.system.exception.badrequest;

public class NotLoggedInException extends RuntimeException {

    public NotLoggedInException() {
        super("You are not logged in");
    }

}
