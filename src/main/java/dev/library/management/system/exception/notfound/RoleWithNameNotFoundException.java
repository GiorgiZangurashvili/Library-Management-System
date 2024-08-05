package dev.library.management.system.exception.notfound;

import dev.library.management.system.domain.enums.RoleName;

public class RoleWithNameNotFoundException extends NotFoundException {

    public RoleWithNameNotFoundException(RoleName roleName) {
        super(String.format("Role with name %s not found", roleName));
    }

}
