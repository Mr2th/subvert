package com.subvert.server.common.util;

import com.subvert.server.common.enums.ResultCode;
import com.subvert.server.common.exception.GlobalException;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author xujianguo
 * @date 2025/3/26
 * @description
 */

@Component
public class RedissonUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedissonUtil.class);

    private static final String USER_TOKEN_KEY_PRE = "user:";

    @Resource
    private RedissonClient redissonClient;

    // 保存token
    public void storeToken(String username, String newToken, Integer expireTime) {
        logger.info("[token：{}存储]", newToken);

        RBucket<String> bucket = redissonClient.getBucket(USER_TOKEN_KEY_PRE + username);
        String oldToken = bucket.get();
        // 判断当前用户对应的token是否存在，不存在则添加
        if (StringUtils.isBlank(oldToken)) {
            bucket.set(newToken, Duration.ofMillis(expireTime));
            return;
        }
        // 存在更新过期时间
        bucket.expire(Duration.ofMillis(expireTime));
    }

    // 删除token
    public void removeToken(String username) {
        logger.info("[删除过期token]");
        redissonClient.getBucket(USER_TOKEN_KEY_PRE + username).delete();
    }

    // 验证token是否相等
    public boolean validateToken(String username, String nowToken) {
        RBucket<String> bucket = redissonClient.getBucket(USER_TOKEN_KEY_PRE + username);
        String passToken = bucket.get();
        return !nowToken.equals(passToken);
    }
}
