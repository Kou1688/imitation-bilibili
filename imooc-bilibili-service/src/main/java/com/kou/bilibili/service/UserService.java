package com.kou.bilibili.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kou.bilibili.domian.entity.UserEntity;

/**
 * @author Kou
 * @description 针对表【t_user(用户表)】的数据库操作Service
 * @createDate 2022-07-06 16:00:49
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 用户注册
     *
     * @param user 用户注册信息
     */
    void addUser(UserEntity user);
}
