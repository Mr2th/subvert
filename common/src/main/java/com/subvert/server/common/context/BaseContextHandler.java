package com.subvert.server.common.context;

import com.subvert.server.common.enums.ResultCode;
import com.subvert.server.common.exception.GlobalException;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xujianguo
 * @date 2025/3/25
 * @description
 */

public class BaseContextHandler {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static final String USER_NAME = "userName";

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static String getUserName() {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }

        if (CollectionUtils.isEmpty(map)) {
            throw new GlobalException(ResultCode.GET_USER_ERROR_CODE);
        }

        return String.valueOf(map.get(USER_NAME));
    }
}

