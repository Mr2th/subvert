package com.subvert.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.subvert.server.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    void insertUser(UserEntity userEntity);

    UserEntity selectUserById(Long userId);

    List<UserEntity> selectUser();

    void deleteUser(Long userId);

    void updateUser(UserEntity userEntity);
}
