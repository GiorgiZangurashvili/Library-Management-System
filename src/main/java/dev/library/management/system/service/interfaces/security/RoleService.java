package dev.library.management.system.service.interfaces.security;

import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.domain.enums.RoleName;
import dev.library.management.system.exception.notfound.EntityNotFoundException;
import dev.library.management.system.exception.notfound.RoleWithNameNotFoundException;

public interface RoleService {

    UserWithRoleResponseDto assignRoleToUser(String username, RoleName roleName)
            throws RoleWithNameNotFoundException, EntityNotFoundException;

}
