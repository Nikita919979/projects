package com.epam.rd.edu.petproject.utils.aspect;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeCheck {

  @Pointcut("@annotation(com.epam.rd.edu.petproject.utils.aspect.Timed)")
  public void timed() {

  }

  @Around("timed()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    long startTime = System.currentTimeMillis();
    MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    Method method = methodSignature.getMethod();
    Object result = pjp.proceed();
    long executionTime = System.currentTimeMillis() - startTime;
    log.info("Method: " + method.getName() + ", Execution time: " + executionTime);
    return result;
  }
}
