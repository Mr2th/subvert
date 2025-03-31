package com.subvert.server.controller;

import com.subvert.server.common.util.Result;
import com.subvert.server.dto.LoginDto;
import com.subvert.server.dto.LogoutDto;
import com.subvert.server.service.UserService;
import com.subvert.server.vo.LoginVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujianguo
 * @date 2025/3/25
 * @description
 */

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("login")
    public Result<LoginVo> login(@RequestBody @Validated LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @GetMapping("logout")
    public Result logout(@RequestHeader HttpServletRequest request, @Validated LogoutDto logoutDto) {
        return userService.logout(logoutDto);
    }
}
