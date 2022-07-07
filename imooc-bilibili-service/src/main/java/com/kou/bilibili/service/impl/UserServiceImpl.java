package com.kou.bilibili.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.bilibili.dao.UserInfoMapper;
import com.kou.bilibili.dao.UserMapper;
import com.kou.bilibili.domian.entity.UserEntity;
import com.kou.bilibili.domian.entity.UserInfoEntity;
import com.kou.bilibili.service.UserService;
import com.kou.bilibli.constants.Constants;
import com.kou.bilibli.exception.ConditionException;
import com.kou.bilibli.util.MD5Util;
import com.kou.bilibli.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author Kou
 * @description 针对表【t_user(用户表)】的数据库操作Service实现
 * @createDate 2022-07-06 16:00:49
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
        implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public void addUser(UserEntity request) {
        //判空
        if (StringUtils.isBlank(request.getPhone())) {
            log.error("注册手机号不能为空");
            throw new ConditionException("手机号不能为空!");
        }
        //是否已注册?
        UserEntity userDo = userMapper.selectByPhone(request.getPhone());
        if (ObjectUtil.isNotNull(userDo)) {
            log.error("用户:" + request.getPhone() + "已经被注册!");
            throw new ConditionException("用户已经注册!");
        }
        //存储用户表信息
        UserEntity user = new UserEntity();
        buildAddUserEntity(request, user);
        userMapper.insert(user);
        //存储用户信息表信息
        UserInfoEntity userInfo = new UserInfoEntity();
        buildAddUserInfoEntity(user, userInfo);
        userInfoMapper.insert(userInfo);
    }

    /**
     * 构建用户注册基本信息实体
     *
     * @param user     用户
     * @param userInfo 用户信息
     */
    private void buildAddUserInfoEntity(UserEntity user, UserInfoEntity userInfo) {
        userInfo.setUserid(user.getId());
        userInfo.setBirth(Constants.DEFAULT_BIRTH);
        userInfo.setGender(Constants.GENDER_UNKNOWN);
        userInfo.setNick(Constants.DEFAULT_NICK);
        userInfo.setSign(Constants.DEFAULT_SIGN);
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 构建用户注册实体
     *
     * @param request 请求体
     * @param user    用户实体
     */
    private void buildAddUserEntity(UserEntity request, UserEntity user) {
        BeanUtils.copyProperties(request, user);

        //密码加密
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
            log.info("用户手机号:{}注册,密码解密成功,原密码为:{}", request.getPhone(), rawPassword);
        } catch (Exception e) {
            log.error("用户注册,密码解密失败,手机号:{}", request.getPhone());
            throw new ConditionException("密码解密失败");
        }
        //将rsa解密出的原密码使用MD5加密进行存储
        String salt = LocalDateTime.now().toString();
        String md5Password = MD5Util.sign(rawPassword, salt, Constants.CHARACTER_SET);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
    }
}




