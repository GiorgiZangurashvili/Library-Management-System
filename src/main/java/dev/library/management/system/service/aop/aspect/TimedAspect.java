package dev.library.management.system.service.aop.aspect;

import dev.library.management.system.service.aop.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimedAspect {

    @Around("@within(timed)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint, Timed timed) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("Execution time of {}.{}: {} ms",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    endTime - startTime);
        }
        return result;
    }

}
