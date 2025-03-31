package com.subvert.server.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.subvert.server.common.exception.GlobalException;
import com.subvert.server.common.util.Result;
import com.subvert.server.common.util.auth.HybridEncryptionUtil;
import com.subvert.server.common.util.auth.JWTUtil;
import com.subvert.server.dto.LoginDto;
import com.subvert.server.dto.LogoutDto;
import com.subvert.server.service.UserService;
import com.subvert.server.vo.LoginVo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author xujianguo
 * @date 2025/3/28
 * @description
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private JWTUtil jwtUtil;

    @Override
    public Result<LoginVo> login(LoginDto loginDto) {
        logger.info("[登录]");

        try {
            // 生成RSA密钥对
            KeyPair rsaKeyPair = HybridEncryptionUtil.generateRSAKeyPair();

            SecretKey decryptedAesKey = HybridEncryptionUtil
                    .decryptAESKeyWithRSA(HybridEncryptionUtil.decodeBase64(loginDto.getLoginInfo()), rsaKeyPair.getPrivate());

            // 使用解密后的AES密钥解密数据
            byte[] decryptedData = HybridEncryptionUtil
                    .decryptDataWithAES(HybridEncryptionUtil.decodeBase64(loginDto.getLoginInfo()), decryptedAesKey);

            JSONObject object = JSON.parseObject(new String(decryptedData), JSONObject.class);
            String username = object.getString("username");
            String password = object.getString("password");

            return Result.success(new LoginVo(jwtUtil.generateAccessToken(username, password)));
        } catch (Exception e) {
            throw new GlobalException("登录失败");
        }
    }

    @Override
    public Result logout(LogoutDto logoutDto) {
        logger.info("[登出]");


        return Result.success();
    }
}
