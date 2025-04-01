package com.subvert.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.subvert.server.common.util.DateUtil;
import com.subvert.server.common.util.PageUtil;
import com.subvert.server.common.util.Result;
import com.subvert.server.common.util.SequenceGenerator;
import com.subvert.server.dto.AddUserDto;
import com.subvert.server.dto.GetUserDto;
import com.subvert.server.dto.ModifyUserDto;
import com.subvert.server.dto.QueryPageUserDto;
import com.subvert.server.dto.RemoveUserDto;
import com.subvert.server.entity.UserEntity;
import com.subvert.server.mapper.UserMapper;
import com.subvert.server.service.UserService;
import com.subvert.server.vo.GetUserVo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xujianguo
 * @date 2025/3/28
 * @description
 */

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<GetUserVo> queryUser(GetUserDto getUserDto) {
        logger.info("[用户信息查询]: {}", JSON.toJSONString(getUserDto));

        GetUserVo getUserVo;
        if (getUserDto.getUserId() != null) {
            logger.info("[根据用户id查询用户信息]");
            UserEntity userEntity = userMapper.selectUserById(getUserDto.getUserId());
            getUserVo = new GetUserVo();
            getUserVo.setEmail(userEntity.getEmail());
            getUserVo.setUserId(userEntity.getUserId());
            getUserVo.setPassword(userEntity.getPassword());
            getUserVo.setScreenName(userEntity.getScreenName());
            getUserVo.setPhoneNumber(userEntity.getPhoneNumber());
        } else {
            logger.info("[根据用户名、密码查询用户信息]");
            LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserEntity::getUsername, getUserDto.getUsername());
            queryWrapper.eq(UserEntity::getPassword, getUserDto.getPassword());
            UserEntity userEntity = userMapper.selectOne(queryWrapper);
            getUserVo = new GetUserVo();
            getUserVo.setEmail(userEntity.getEmail());
            getUserVo.setUserId(userEntity.getUserId());
            getUserVo.setPassword(userEntity.getPassword());
            getUserVo.setScreenName(userEntity.getScreenName());
            getUserVo.setPhoneNumber(userEntity.getPhoneNumber());
        }

        logger.info("[用户信息]: {}", JSON.toJSONString(getUserVo));
        return Result.success(getUserVo);
    }

    @Override
    @Transactional
    public void removeUser(RemoveUserDto removeUserDto) {
        logger.info("[用户注销]: {}", removeUserDto.getUserId());
        userMapper.deleteUser(removeUserDto.getUserId());
    }

    @Override
    @Transactional
    public void addUser(AddUserDto addUserDto) {
        logger.info("[用户信息注册]: {}", addUserDto.getUsername());
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(addUserDto.getEmail());
        userEntity.setPassword(addUserDto.getPassword());
        userEntity.setScreenName(addUserDto.getScreenName());
        userEntity.setUsername(addUserDto.getUsername());
        userEntity.setUserId(SequenceGenerator.getSnowFlakeId());
        userEntity.setCreateTime(DateUtil.getDateTime());
        userEntity.setModifyTime(DateUtil.getDateTime());
        userMapper.insertUser(userEntity);
    }

    @Override
    @Transactional
    public void modifyUser(ModifyUserDto modifyUserDto) {
        logger.info("[修改{}用户信息]", modifyUserDto.getUserId());
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(modifyUserDto.getUserId());
        userEntity.setPhoneNumber(modifyUserDto.getPhoneNumber());
        userEntity.setScreenName(modifyUserDto.getScreenName());
        userEntity.setEmail(modifyUserDto.getEmail());
        userEntity.setPassword(modifyUserDto.getPassword());
        userEntity.setModifyTime(DateUtil.getDateTime());
        userMapper.updateUser(userEntity);
    }

    @Override
    public Result<PageUtil> queryPageUser(QueryPageUserDto queryPageUserDto) {
        logger.info("[用户信息分页查询]: {}", JSON.toJSONString(queryPageUserDto));
        IPage<UserEntity> page = new PageDTO<>();
        page.setCurrent(queryPageUserDto.getPageIndex());
        page.setSize(queryPageUserDto.getPageSize());
        page = baseMapper.selectPage(page, new LambdaQueryWrapper<UserEntity>()
                .like(UserEntity::getScreenName, queryPageUserDto.getSearchWord())
                .like(UserEntity::getUsername, queryPageUserDto.getSearchWord()));

        if (CollUtil.isNotEmpty(page.getRecords())) {
            page.getRecords().forEach(userEntity -> userEntity.setPassword(null));
        }

        return Result.success(new PageUtil(page));
    }

    @Override
    @Transactional
    public void removeUsers(RemoveUserDto removeUserDto) {
        logger.info("[多用户注销]: {}", JSON.toJSONString(removeUserDto.getUserIds()));
        baseMapper.deleteByIds(removeUserDto.getUserIds());
    }
}
