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
import dev.library.management.system.service.aop.annotation.Loggable;
import dev.library.management.system.service.interfaces.UserService;
import dev.library.management.system.service.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Loggable(className = "UserServiceImpl")
// TODO ADD ENABLE ACCOUNT
public class UserServiceImpl implements UserService {
    /* Repositories */
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /* Mappers */
    private final UserMapper userMapper;

    /* PasswordEncoder */
    private final PasswordEncoder passwordEncoder;

    /* Properties */
    @Value("${disabled-account-duration}")
    private int disabledAccountDuration;

    @Override
    @Transactional
    public UserWithRoleResponseDto saveUser(final UserRequestDto userRequestDto)
            throws RoleWithNameNotFoundException {
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
    public UserResponseDto updateUserCredentials(final UserRequestDto userRequestDto)
            throws NotLoggedInException, UsernameNotFoundException {
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

    @Override
    public UserResponseDto disableUserById(final long id) throws EntityNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            log.error("Error while deleting a user with id = {}, Reason: Not Found", id);
            throw new EntityNotFoundException(EntityName.User, id);
        }

        User user = userOptional.get();
        user.setDisabled(true);
        user.setDisabledDate(LocalDateTime.now());

        return userMapper.mapUserToUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto disableUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            log.error("Error while deleting a user with name = {}, Reason: Not Found", username);
            throw new UsernameNotFoundException(username);
        }

        User user = userOptional.get();
        user.setDisabled(true);
        user.setDisabledDate(LocalDateTime.now());

        return userMapper.mapUserToUserResponseDto(userRepository.save(user));
    }

    //TODO create query that will delete users
    // NOT OPTIMAL
    @Override
    @Transactional
    public void deleteDisabledUsers() {
        List<User> disabledUsers = userRepository.findAllByDisabled(true);
        List<Long> filteredDisabledUserIds = disabledUsers
                .stream()
                .filter(user -> ChronoUnit.DAYS
                        .between(user.getDisabledDate(), LocalDateTime.now()) >= disabledAccountDuration)
                .map(User::getId)
                .toList();

        userRepository.deleteByIdIn(filteredDisabledUserIds);
    }
}
