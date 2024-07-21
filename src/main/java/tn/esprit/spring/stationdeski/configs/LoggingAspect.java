package tn.esprit.spring.stationdeski.configs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("execution(* tn.esprit.spring.stationdeski.services.SkieurService.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In method " + name + " : ");
    }

    @After("execution(* tn.esprit.spring.stationdeski.services.AbonnementService.*(..))")
    public void logMethodEntry1(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In method " + name + " : ");
    }

    @AfterReturning("execution(* tn.esprit.spring.stationdeski.services.MoniteurService.*(..))")
    public void logMethodEntry2(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In method " + name + " : ");
    }

    @AfterThrowing("execution(* tn.esprit.spring.stationdeski.services.CoursService.*(..))")
    public void logMethodEntry3(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In method " + name + " : ");
    }

    /* @Around("execution(* tn.esprit.spring.stationdeski.services.PisteService.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method execution time: " + elapsedTime + " milliseconds.");
        return obj;
    } */
}
