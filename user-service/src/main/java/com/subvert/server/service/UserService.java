package com.subvert.server.service;

import com.subvert.server.common.util.PageUtil;
import com.subvert.server.common.util.Result;
import com.subvert.server.dto.AddUserDto;
import com.subvert.server.dto.GetUserDto;
import com.subvert.server.dto.ModifyUserDto;
import com.subvert.server.dto.QueryPageUserDto;
import com.subvert.server.dto.RemoveUserDto;
import com.subvert.server.vo.GetUserVo;

import java.util.List;

/**
 * @author xujianguo
 * @date 2025/3/28
 * @description
 */

public interface UserService {

    Result<GetUserVo> queryUser(GetUserDto getUserDto);

    void removeUser(RemoveUserDto removeUserDto);

    void addUser(AddUserDto addUserDto);

    void modifyUser(ModifyUserDto modifyUserDto);

    Result<PageUtil> queryPageUser(QueryPageUserDto queryPageUserDto);

    void removeUsers(RemoveUserDto removeUserDto);
}
