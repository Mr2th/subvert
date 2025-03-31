package com.subvert.server.common.util.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.subvert.server.api.UserRemoteService;
import com.subvert.server.common.enums.ResultCode;
import com.subvert.server.common.exception.GlobalException;
import com.subvert.server.common.util.RedissonUtil;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xujianguo
 * @date 2025/3/25
 * @description JWT 令牌工具
 */

@Component
public class JWTUtil {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private Integer expirationTime;

    @Resource
    private RedissonUtil redissonUtil;

    @DubboReference
    private UserRemoteService userRemoteService;

    /**
     * 生成短期访问token
     */
    public String generateAccessToken(String username, String password) {
        logger.info("[AccessToken生成]：{}", username);

        if (Boolean.FALSE.equals(userRemoteService.userExistenceChecker(username, password))) {
            throw new GlobalException("用户名或密码错误");
        }

        String token = JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())  // 设置签发时间
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))  // 设置过期时间
                .sign(Algorithm.HMAC256(secretKey));
        // 缓存token
        redissonUtil.storeToken(username, token, expirationTime);
        return token;
    }


    /**
     * 解析token
     */
    public boolean validateToken(String token) {
        try {
            logger.info("[token解析]：{}", token);
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);

            Date expiresAt = verify.getExpiresAt();
            String username = verify.getSubject();
            if (expiresAt != null && expiresAt.before(new Date())) {
                redissonUtil.removeToken(username); // 同步删除redis缓存
                throw new GlobalException("token已过期");
            }

            if (redissonUtil.validateToken(username, token)) {
                throw new GlobalException(ResultCode.AUTH_ERROR_CODE.getCode(), "当前用户已在其它地方登录");
            }

            return true;
        } catch (Exception e) {
            logger.error("[token失效或已过期]");
            return false;
        }
    }
}
