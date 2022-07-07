package com.kou.bilibili.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.kou.bilibili.domian.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Kou
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2022-07-06 16:00:49
* @Entity com.kou.bilibili.domian.entity.UserEntity
*/
public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity selectByPhone(@Param("phone") String phone);
}




