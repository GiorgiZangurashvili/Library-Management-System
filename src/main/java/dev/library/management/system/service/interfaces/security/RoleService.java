package dev.library.management.system.service.interfaces.security;

import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.domain.enums.RoleName;
import dev.library.management.system.exception.notfound.RoleWithNameNotFoundException;
import dev.library.management.system.exception.notfound.UsernameNotFoundException;

public interface RoleService {

    /**
     * Assigns a role to a user.
     *
     * @param username The username of the user to whom the role will be assigned.
     * @param roleName The name of the role to be assigned.
     * @return A UserWithRoleResponseDto object representing the user with updated role information.
     * @throws RoleWithNameNotFoundException If no role is found with the given name.
     * @throws UsernameNotFoundException If no user is found with the given username.
     */
    UserWithRoleResponseDto assignRoleToUser(String username, RoleName roleName)
            throws RoleWithNameNotFoundException, UsernameNotFoundException;

}
