package com.subvert.server.remote;

import com.subvert.server.api.UserRemoteService;
import com.subvert.server.common.enums.ResultCode;
import com.subvert.server.common.exception.GlobalException;
import com.subvert.server.common.util.Result;
import com.subvert.server.dto.GetUserDto;
import com.subvert.server.service.UserService;
import com.subvert.server.vo.GetUserVo;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.hibernate.procedure.NoSuchParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xujianguo
 * @date 2025/3/26
 * @description
 */

@DubboService
public class UserRemoteServiceImpl implements UserRemoteService {

    private static final Logger logger = LoggerFactory.getLogger(UserRemoteServiceImpl.class);

    @Resource
    private UserService userService;

    @Override
    public Boolean userExistenceChecker(String username, String password) {
        logger.info("[登录用户验证]");

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new GlobalException(ResultCode.VALIDA_ERROR_CODE.getCode(), "登录信息为空");
        }

        Result<GetUserVo> result = userService.queryUser(new GetUserDto(username, password));
        if (result.isSuccess()
                && (result.getData() != null
                && username.equals(result.getData().getUsername()) && password.equals(result.getData().getPassword()))) {
                logger.info("[验证成功]");
                return Boolean.TRUE;

        }

        logger.info("[登录信息验证失败，用户名：{}]", username);
        return Boolean.FALSE;
    }
}
