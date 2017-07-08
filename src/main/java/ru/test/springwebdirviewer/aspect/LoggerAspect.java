package ru.test.springwebdirviewer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Aspect
public class LoggerAspect {

    @Pointcut("execution(* *(..)) && within(ru.test.springwebdirviewer.service.*)")
    private void allMethods() {
    }

    @Around("allMethods() && @annotation(ru.test.springwebdirviewer.annotation.ExecTime)")
    public Object methodExecutionTime(ProceedingJoinPoint joinPoint) {

        Object output = null;

        System.out.println(String.format("--> " + this.getClass().getSimpleName() + ": " + joinPoint.getSignature().toShortString()));

        for (Object object : joinPoint.getArgs()) {
            System.out.println("Param: " + object);
        }

        long startTime = Instant.now().toEpochMilli();

        try {
            output = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long endTime = Instant.now().toEpochMilli();

        System.out.println(String.format("Completed in %d milliseconds", (endTime - startTime)));

        return output;
    }
}
