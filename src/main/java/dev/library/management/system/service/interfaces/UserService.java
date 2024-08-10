package dev.library.management.system.service.interfaces;

import dev.library.management.system.domain.dto.request.UserRequestDto;
import dev.library.management.system.domain.dto.response.UserResponseDto;
import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.exception.badrequest.NotLoggedInException;
import dev.library.management.system.exception.notfound.EntityNotFoundException;
import dev.library.management.system.exception.notfound.RoleWithNameNotFoundException;
import dev.library.management.system.exception.notfound.UsernameNotFoundException;

public interface UserService {

    /**
     * Saves a new user to the database.
     *
     * @param userRequestDto The UserRequestDto object containing the user details to save.
     * @return A UserWithRoleResponseDto object representing the saved user with role information.
     * @throws RoleWithNameNotFoundException If the default user role is not found in the database.
     */
    UserWithRoleResponseDto saveUser(UserRequestDto userRequestDto) throws RoleWithNameNotFoundException;

    /**
     * Updates the credentials of the currently logged-in user.
     *
     * @param userRequestDto The UserRequestDto object containing the new user credentials.
     * @return A UserResponseDto object representing the updated user.
     * @throws NotLoggedInException If there is no currently logged-in user.
     * @throws UsernameNotFoundException If the current user is not found in the database.
     */
    UserResponseDto updateUserCredentials(UserRequestDto userRequestDto)
            throws NotLoggedInException, UsernameNotFoundException;

    /**
     * Disables a user from the database by their ID.
     *
     * @param id The ID of the user to delete.
     * @return A UserResponseDto object representing the deleted user.
     * @throws EntityNotFoundException If no user is found with the given ID.
     */
    UserResponseDto disableUserById(long id) throws EntityNotFoundException;

    /**
     * Disables a user from the database by their username.
     *
     * @param username The username of the user to delete.
     * @return A UserResponseDto object representing the deleted user.
     * @throws UsernameNotFoundException If no user is found with the given username.
     */
    UserResponseDto disableUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Deletes users from the database by their ids.
     */
    void deleteDisabledUsers();

}
