package dev.library.management.system.exception.notfound;


public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException(String username) {
        super(String.format("User with username = '%s' not found", username));
    }
}
