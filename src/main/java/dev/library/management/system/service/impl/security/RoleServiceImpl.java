package dev.library.management.system.service.impl.security;

import dev.library.management.system.domain.dto.response.UserWithRoleResponseDto;
import dev.library.management.system.domain.entity.User;
import dev.library.management.system.domain.entity.security.Role;
import dev.library.management.system.domain.enums.RoleName;
import dev.library.management.system.domain.mapper.UserMapper;
import dev.library.management.system.exception.notfound.RoleWithNameNotFoundException;
import dev.library.management.system.exception.notfound.UsernameNotFoundException;
import dev.library.management.system.repository.UserRepository;
import dev.library.management.system.repository.security.RoleRepository;
import dev.library.management.system.service.aop.annotation.Loggable;
import dev.library.management.system.service.interfaces.security.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Loggable(className = "RoleServiceImpl")
public class RoleServiceImpl implements RoleService {
    /* Repositories */
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    /* Mappers */
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserWithRoleResponseDto assignRoleToUser(final String username, final RoleName roleName)
            throws RoleWithNameNotFoundException, UsernameNotFoundException {
        Optional<Role> roleOptional = roleRepository.findRoleByRoleName(roleName);

        if (roleOptional.isEmpty()) {
            log.error("Error while retrieving role with name = {}, Reason: Not Found", roleName.name());
            throw new RoleWithNameNotFoundException(roleName);
        }

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            log.error("Error while retrieving user with name = {}, Reason: Not Found", username);
            throw new UsernameNotFoundException(username);
        }

        Role role = roleOptional.get();
        User user = userOptional.get();
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
        }

        return userMapper.mapUserToUserWithRoleResponseDto(user);
    }

}
