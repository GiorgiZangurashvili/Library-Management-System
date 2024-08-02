package dev.library.management.system.exception.notfound;

import dev.library.management.system.domain.enums.EntityName;

public class EntityNotFoundException extends NotFoundException {

    public EntityNotFoundException(final EntityName entityName, final long id) {
        super(String.format("%s with id = %d was not found", entityName.name(), id));
    }

}
