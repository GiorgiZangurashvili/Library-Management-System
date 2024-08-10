package dev.library.management.system.service.aop.aspect;

import dev.library.management.system.service.aop.annotation.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Aspect
@Component
@Slf4j
public class LoggableAspect {

    @Before("@within(loggable)")
    public void logClassName(JoinPoint joinPoint, Loggable loggable) {
        String className = loggable.className();
        log.info("*** In: {} ***", className);
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Class<?>[] paramTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();

        StringJoiner params = new StringJoiner(", ");
        for (int i = 0; i < args.length; i++) {
            params.add(paramNames[i] + ": " + paramTypes[i].getSimpleName() + " = " + args[i]);
        }

        log.info("*** {}({}) method called ***", methodName, params);
    }

}
