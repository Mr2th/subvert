package com.subvert.server.controller;

import com.subvert.server.common.util.Result;
import com.subvert.server.dto.LoginDto;
import com.subvert.server.dto.LogoutDto;
import com.subvert.server.service.LoginService;
import com.subvert.server.service.UserService;
import com.subvert.server.vo.LoginVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@RestController
@RequestMapping("/api")
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping("login")
    public Result<LoginVo> login(@RequestBody @Validated LoginDto loginDto) {
        return loginService.login(loginDto);
    }

    @GetMapping("logout")
    public Result logout(@RequestHeader HttpServletRequest request, @Validated LogoutDto logoutDto) {
        return loginService.logout(logoutDto);
    }
}
