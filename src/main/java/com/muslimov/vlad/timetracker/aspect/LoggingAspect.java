package com.muslimov.vlad.timetracker.aspect;

import com.muslimov.vlad.timetracker.model.MethodTracker;
import com.muslimov.vlad.timetracker.service.MethodTrackerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    private final MethodTrackerService methodTrackerService;

    @Pointcut("@annotation(com.muslimov.vlad.timetracker.annotation.TrackTime)")
    public void annotationTrackTimePointcut() {
    }

    @Pointcut("@annotation(com.muslimov.vlad.timetracker.annotation.TrackAsyncTime)")
    public void annotationTrackAsyncTimePointcut() {
    }

    @Around("annotationTrackTimePointcut()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) {
        return writeAndSaveLog(proceedingJoinPoint);
    }

    @Around("annotationTrackAsyncTimePointcut()")
    public Object measureAsyncMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) {

        return CompletableFuture.runAsync(() -> writeAndSaveLog(proceedingJoinPoint));
    }

    private Object writeAndSaveLog(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("writeAndSaveLog in thread " + Thread.currentThread().getName());

        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object result;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            log.error("Error during execution method");
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();

        methodTrackerService.saveMethodTracker(
            MethodTracker.builder()
                .methodName(methodName)
                .executionTime(endTime - startTime)
                .build()
        );

        return result;
    }
}