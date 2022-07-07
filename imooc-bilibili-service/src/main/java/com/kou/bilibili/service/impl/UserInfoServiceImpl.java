package com.kou.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.bilibili.domian.entity.UserInfo;
import com.kou.bilibili.dao.UserInfoMapper;
import com.kou.bilibili.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
* @author Kou
* @description 针对表【t_user_info(用户基本信息表)】的数据库操作Service实现
* @createDate 2022-07-06 16:00:09
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




