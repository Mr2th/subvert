package com.subvert.server.common.inteceptor;

import com.subvert.server.common.util.auth.JWTUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author xujianguo
 * @date 2025/3/25
 * @description 路径拦截、token校验
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Resource
    private JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("[Interceptor] 校验权限 - 请求URI: {}, 方法: {}", request.getRequestURI(), request.getMethod());

        String token = request.getHeader("Authorization");
        if (!jwtUtil.validateToken(token)) {
            logger.error("[Interceptor] Token 校验失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("会话失效，请重新登录");
            return false;
        }

        return true;
    }
}
