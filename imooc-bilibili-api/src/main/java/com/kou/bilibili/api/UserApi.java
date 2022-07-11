package com.kou.bilibili.api;

import com.kou.bilibili.api.support.UserSupport;
import com.kou.bilibili.domian.JsonResponse;
import com.kou.bilibili.domian.entity.UserEntity;
import com.kou.bilibili.domian.entity.UserInfoEntity;
import com.kou.bilibili.service.UserInfoService;
import com.kou.bilibili.service.UserService;
import com.kou.bilibli.util.RSAUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author KouChaoJie
 * @since: 2022/7/7 14:35
 */
@RestController
public class UserApi {
    @Resource
    private UserService userService;
    @Resource
    private UserSupport userSupport;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 获取当前用户信息
     */
    @GetMapping("/users")
    public JsonResponse<UserEntity> getUserInfo() {
        UserEntity user = userService.getById(userSupport.getCurrentUserId());
        UserInfoEntity userInfo = userInfoService.getUserInfoByUserId(user.getId());
        user.setUserInfo(userInfo);
        return JsonResponse.success(user);
    }

    /**
     * 获取rsa公钥
     */
    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPks() {
        return JsonResponse.success(RSAUtil.getPublicKeyStr());
    }

    /**
     * 用户注册
     */
    @PostMapping("/users")
    public JsonResponse<String> addUser(@RequestBody UserEntity user) {
        userService.addUser(user);
        return JsonResponse.success();
    }

    @PostMapping("/user-tokens")
    public JsonResponse<String> login(@RequestBody UserEntity user) throws Exception {
        String token = userService.login(user);
        return JsonResponse.success(token);
    }
}
