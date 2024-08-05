package dev.library.management.system.service.impl;

import dev.library.management.system.domain.dto.request.UserRequestDto;
import dev.library.management.system.domain.dto.response.UserResponseDto;
import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.domain.entity.User;
import dev.library.management.system.domain.entity.security.Role;
import dev.library.management.system.domain.enums.EntityName;
import dev.library.management.system.domain.enums.RoleName;
import dev.library.management.system.domain.mapper.UserMapper;
import dev.library.management.system.exception.badrequest.NotLoggedInException;
import dev.library.management.system.exception.notfound.EntityNotFoundException;
import dev.library.management.system.exception.notfound.RoleWithNameNotFoundException;
import dev.library.management.system.exception.notfound.UsernameNotFoundException;
import dev.library.management.system.repository.UserRepository;
import dev.library.management.system.repository.security.RoleRepository;
import dev.library.management.system.service.interfaces.UserService;
import dev.library.management.system.service.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    /* Repositories */
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /* Mappers */
    private final UserMapper userMapper;

    /* PasswordEncoder */
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserWithRoleResponseDto saveUser(UserRequestDto userRequestDto) throws RoleWithNameNotFoundException {
        log.info("*** saveUser(UserRequestDto userRequestDto) method called ***)");

        User user = userMapper.mapUserRequestDtoToUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleName roleName = RoleName.USER;
        Optional<Role> userRoleOptional = roleRepository.findRoleByRoleName(roleName);

        if (userRoleOptional.isEmpty()) {
            log.error("Error while retrieving role with name = {}, Reason: Not Found", roleName.name());
            throw new RoleWithNameNotFoundException(roleName);
        }

        Role role = userRoleOptional.get();
        user.setRoles(List.of(role));

        userRepository.save(user);

        return userMapper.mapUserToUserWithRoleResponseDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUserCredentials(UserRequestDto userRequestDto)
            throws NotLoggedInException, UsernameNotFoundException {
        log.info("*** updateUserCredentials(UserRequestDto userRequestDto) method called ***)");

        Optional<String> currentUsernameOptional = SecurityUtil.getCurrentUsername();

        if (currentUsernameOptional.isEmpty()) {
            log.error("Error while getting current username, Reason: Not Logged In");
            throw new NotLoggedInException();
        }

        String currentUsername = currentUsernameOptional.get();
        Optional<User> currentUserOptional = userRepository.findByUsername(currentUsername);

        if (currentUserOptional.isEmpty()) {
            log.error("Error while retrieving user with username = '{}', Reason: Not Found", currentUsername);
            throw new UsernameNotFoundException(currentUsername);
        }

        User user = currentUserOptional.get();
        user.setUsername(userRequestDto.username());
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));

        return userMapper.mapUserToUserResponseDto(userRepository.save(user));
    }


    //TODO add disabling feature
    // When user tries to delete his/her account
    // at first account will be disabled,
    // after 30 days it will be deleted
    @Override
    @Transactional
    public UserResponseDto deleteUserById(long id) throws EntityNotFoundException {
        log.info("*** deleteUserById(long id) method called ***)");

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            log.error("Error while deleting a user with id = {}, Reason: Not Found", id);
            throw new EntityNotFoundException(EntityName.User, id);
        }

        userRepository.deleteById(id);

        return userMapper.mapUserToUserResponseDto(user.get());
    }

    @Override
    @Transactional
    public UserResponseDto deleteUserByUsername(String username) throws UsernameNotFoundException {
        log.info("*** deleteUserByUsername(String username) method called ***)");

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            log.error("Error while deleting a user with name = {}, Reason: Not Found", username);
            throw new UsernameNotFoundException(username);
        }

        userRepository.deleteByUsername(username);

        return userMapper.mapUserToUserResponseDto(user.get());
    }
}
