package com.subvert.server.api;

/**
 * @author xujianguo
 * @date 2025/3/26
 * @description
 */

public interface UserRemoteService {

    Boolean userExistenceChecker(String username, String password);
}
