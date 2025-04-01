package com.subvert.server.common.util;

import cn.hutool.core.util.IdUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

public class SequenceGenerator {

    private SequenceGenerator() {
        throw new AssertionError("SequenceGenerator cannot be instantiated");
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final int MAX_COUNTER = 999;

    private static final ReentrantLock lock = new ReentrantLock();

    public static String generateSequence() {
        lock.lock();
        try {

            String timestamp = dateFormat.format(new Date());
            int count = counter.getAndIncrement();
            // 如果计数器超过最大值，重置为0
            if (count > MAX_COUNTER) {
                counter.set(0);
                count = counter.getAndIncrement();
            }

            String formattedCount = String.format("%03d", count);
            return timestamp + formattedCount;
        } finally {
            lock.unlock();
        }
    }

    public static Long getSnowFlakeId() {
        return IdUtil.getSnowflakeNextId();
    }
}
