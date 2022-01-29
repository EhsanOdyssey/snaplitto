package ir.snapp.pay.billsharing.config.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Sat 29 Jan 2022
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(
            "within(ir.snapp.pay.billsharing.controller..*)" +
            "|| within(ir.snapp.pay.billsharing.service..*)" +
            "|| within(ir.snapp.pay.billsharing.repository..*)"
    )
    public void packagesPointcut() {}

    @Pointcut("@annotation(ir.snapp.pay.billsharing.config.logging.Loggable)")
    public void loggableMethods() {}

    @AfterThrowing(pointcut = "packagesPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception occurred in {} > {}() with cause --> {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                (e.getMessage() != null ? e.getMessage() : "EMPTY"));
    }

    @Around("packagesPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter: {} > {}() with argument[s] -->\n{}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (logger.isDebugEnabled()) {
                logger.debug("Exit: {} > {}() with result -->\n{}",
                        joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(),
                        result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal arguments passed to {} > {}() --> {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
            throw e;
        }
    }

    @Around("loggableMethods()")
    public Object logAroundLoggable(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Enter: {} > {}() with argument[s] -->\n{}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        try {
            Object result = joinPoint.proceed();
            logger.info("Exit: {} > {}() with result -->\n{}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    result);
            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal arguments passed to {} > {}() --> {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
            throw e;
        }
    }

    @Around("@annotation(ir.snapp.pay.billsharing.config.logging.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long startMillis = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        final long executionTimeMillis = System.currentTimeMillis() - startMillis;
        logger.info("{} > {}() executed in --> {} ms",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                executionTimeMillis);
        return result;
    }
}
