package com.subvert.server.common.config;

import com.subvert.server.common.enums.ResultCode;
import com.subvert.server.common.exception.GlobalException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author xujianguo
 * @date 2025/3/25
 * @description 接口耗时统计
 */
@Aspect
@Component
public class LogExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogExecutionTimeAspect.class);

    @Around("execution(* com.subvert.server..*(..)) && @within(org.springframework.web.bind.annotation.RestController)") // 拦截 Controller 方法
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime(); // 使用高精度时间戳
        Object result;
        try {
            result = joinPoint.proceed(); // 执行目标方法
        } catch (GlobalException e) {
            throw new GlobalException(ResultCode.UNKNOWN_ERROR_CODE.getCode()
                    , "[AOP]: {" + joinPoint.getSignature() + "}方法执行异常:" + e.getMessage()); // 重新抛出异常
        }
        long end = System.nanoTime();
        long duration = end - start;
        logger.info("[AOP]: 方法 [{}] 执行耗时: {} 纳秒", joinPoint.getSignature(), duration);
        return result;
    }
}
