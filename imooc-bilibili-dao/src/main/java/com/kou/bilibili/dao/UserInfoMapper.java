package com.kou.bilibili.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kou.bilibili.domian.entity.UserInfoEntity;

/**
* @author Kou
* @description 针对表【t_user_info(用户基本信息表)】的数据库操作Mapper
* @createDate 2022-07-06 16:00:09
* @Entity com.kou.bilibili.domian.entity.UserInfo
*/
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {

    UserInfoEntity selectByUserid(@Param("userid") Long userid);
}




