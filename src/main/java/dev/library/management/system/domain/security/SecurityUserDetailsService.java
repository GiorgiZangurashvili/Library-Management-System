package dev.library.management.system.domain.security;

import dev.library.management.system.domain.entity.User;
import dev.library.management.system.exception.notfound.UsernameNotFoundException;
import dev.library.management.system.repository.UserRepository;
import dev.library.management.system.service.aop.annotation.Loggable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Loggable(className = "SecurityUserDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        return user.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
