package com.kou.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.bilibili.domian.entity.UserEntity;
import com.kou.bilibili.dao.UserMapper;
import com.kou.bilibili.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author Kou
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2022-07-06 16:00:49
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserService{

}




