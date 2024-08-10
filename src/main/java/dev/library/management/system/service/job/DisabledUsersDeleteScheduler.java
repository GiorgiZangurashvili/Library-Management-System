package dev.library.management.system.service.job;

import dev.library.management.system.service.aop.annotation.Loggable;
import dev.library.management.system.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Loggable(className = "DisabledUsersDeleteScheduler")
public class DisabledUsersDeleteScheduler {
    private final UserService userServiceImpl;

    // cron expression - This method
    // will execute every midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteDisabledUsers() {
        userServiceImpl.deleteDisabledUsers();
    }

}
