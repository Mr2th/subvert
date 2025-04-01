package com.subvert.server.common.util;

import cn.hutool.core.date.DateTime;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

public class DateUtil {

    private DateUtil() {
        throw new AssertionError("DateUtil cannot be instantiated");
    }

    private static final String YEAR = "yyyy";

    private static final String DATE = "yyyy-MM-dd";

    private static final String TIME = "HH:mm:ss";

    private static final String TIME_MS = "HH:mm:ss:SSS";

    private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_TIME_MS = "yyy-MM-dd HH:mm:ss:SSS";

    public static DateTime getDateTime() {
        return new DateTime(DATE_TIME);
    }

    public static DateTime getDateTime(String pattern) {
        return new DateTime(pattern);
    }
}
