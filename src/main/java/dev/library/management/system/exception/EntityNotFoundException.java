package dev.library.management.system.exception;

import dev.library.management.system.domain.enums.EntityName;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(final EntityName entityName, final long id) {
        super(String.format("%s with id = %d was not found", entityName.name(), id));
    }

}
