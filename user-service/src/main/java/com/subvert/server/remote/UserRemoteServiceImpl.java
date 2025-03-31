package com.subvert.server.remote;

import com.subvert.server.api.UserRemoteService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author xujianguo
 * @date 2025/3/26
 * @description
 */

@DubboService
public class UserRemoteServiceImpl implements UserRemoteService {
    @Override
    public Boolean userExistenceChecker(String username, String password) {
        return null;
    }
}
