package az.ingress.ms.common.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut(value = "execution(* az.ingress.ms.service.*.*(..))")
    public void elapsedTime(){

    }

    @SneakyThrows
    @Around(value = "elapsedTime()")
    public void elapsedTimeLogger(ProceedingJoinPoint jp){
        long startDate = System.currentTimeMillis();
        jp.proceed();
        long endDate = System.currentTimeMillis();
        System.out.println(startDate);
        System.out.println(endDate);
        log.info("Elapsed Time : {} ", endDate-startDate);
    }

//    @Before("execution(* az.ingress.ms.*.*.*.*.*(..))")
//    public void logMethodExecutionBefore(JoinPoint joinPoint){
//        String methodName=joinPoint.getSignature().getName();
//        Object[] methodArgs = joinPoint.getArgs();
//        log.info("Before method execution intercepted: {} - Arguments: {}", methodName, Arrays.toString(methodArgs));
//    }
//
//    @AfterReturning(pointcut = "execution(* az.ingress.ms.*.*.*.*.*(..))", returning = "result")
//    public  void logMethodExecutionAfterReturning(JoinPoint joinPoint, Object result){
//        String methodName=joinPoint.getSignature().getName();
//        log.info("Method execution completed: %s - Result: %s ", methodName, result);
//    }
//
//    @AfterThrowing(pointcut = "execution(* az.ingress.ms.*.*.*.*.*(..))", throwing = "exception")
//    public void logMethodExecutionAfterThrowing(JoinPoint joinPoint, Exception exception){
//        String methodName=joinPoint.getSignature().getName();
//        log.warn("Exception occurred during method execution: %s - Exception: %s", methodName, exception);
//    }
}
