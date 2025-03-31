package com.subvert.server.service;

import com.subvert.server.common.util.Result;
import com.subvert.server.dto.LoginDto;
import com.subvert.server.dto.LogoutDto;
import com.subvert.server.vo.LoginVo;

/**
 * @author xujianguo
 * @date 2025/3/28
 * @description
 */

public interface UserService {

    Result<LoginVo> login(LoginDto loginDto);

    Result logout(LogoutDto logoutDto);
}
