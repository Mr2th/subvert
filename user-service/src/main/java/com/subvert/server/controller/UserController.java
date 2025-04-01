package com.subvert.server.controller;

import com.subvert.server.common.util.PageUtil;
import com.subvert.server.common.util.Result;
import com.subvert.server.dto.AddUserDto;
import com.subvert.server.dto.GetUserDto;
import com.subvert.server.dto.LoginDto;
import com.subvert.server.dto.LogoutDto;
import com.subvert.server.dto.ModifyUserDto;
import com.subvert.server.dto.QueryPageUserDto;
import com.subvert.server.dto.RemoveUserDto;
import com.subvert.server.service.UserService;
import com.subvert.server.vo.GetUserVo;
import com.subvert.server.vo.LoginVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/queryUser")
    public Result<GetUserVo> queryUser(@RequestBody GetUserDto getUserDto) {
        return userService.queryUser(getUserDto);
    }

    @PostMapping("/queryPageUser")
    public Result<PageUtil> queryPageUser(@RequestBody QueryPageUserDto queryPageUserDto) {
        return userService.queryPageUser(queryPageUserDto);
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody AddUserDto addUserDto) {
        userService.addUser(addUserDto);
        return Result.success();
    }

    @PostMapping("/removeUser")
    public Result removeUser(@RequestBody RemoveUserDto removeUserDto) {
        userService.removeUser(removeUserDto);
        return Result.success();
    }

    @PostMapping("/modifyUser")
    public Result modifyUser(@RequestBody ModifyUserDto modifyUserDto) {
        userService.modifyUser(modifyUserDto);
        return Result.success();
    }

    @PostMapping("/removeUsers")
    public Result removeUsers(@RequestBody RemoveUserDto removeUserDto) {
        userService.removeUsers(removeUserDto);
        return Result.success();
    }
}
