package com.kou.bilibili.api;

import com.kou.bilibili.domian.JsonResponse;
import com.kou.bilibili.service.UserService;
import com.kou.bilibli.util.RSAUtil;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 获取rsa公钥
     */
    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPks() {
        return JsonResponse.success(RSAUtil.getPublicKeyStr());
    }
}