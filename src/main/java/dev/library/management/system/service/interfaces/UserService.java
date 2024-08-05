package dev.library.management.system.service.interfaces;

import dev.library.management.system.domain.dto.request.UserRequestDto;
import dev.library.management.system.domain.dto.response.UserResponseDto;
import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.exception.badrequest.NotLoggedInException;
import dev.library.management.system.exception.notfound.EntityNotFoundException;
import dev.library.management.system.exception.notfound.UsernameNotFoundException;

public interface UserService {

    UserWithRoleResponseDto saveUser(UserRequestDto userRequestDto);

    UserResponseDto updateUserCredentials(UserRequestDto userRequestDto)
            throws NotLoggedInException, UsernameNotFoundException;

    UserResponseDto deleteUserById(long id) throws EntityNotFoundException;
    UserResponseDto deleteUserByUsername(String username) throws UsernameNotFoundException;

}
