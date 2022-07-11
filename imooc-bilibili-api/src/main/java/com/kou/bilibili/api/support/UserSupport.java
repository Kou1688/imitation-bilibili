package com.kou.bilibili.api.support;

import com.kou.bilibli.constants.Constants;
import com.kou.bilibli.exception.ConditionException;
import com.kou.bilibli.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 用户服务支持类
 *
 * @author KouChaoJie
 * @since: 2022/7/7 17:30
 */
@Component
public class UserSupport {
    /**
     * 获取当前用户id
     *
     * @return
     */
    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        //获取请求头中的token
        String token = requestAttributes.getRequest().getHeader(Constants.TOKEN);
        if (TokenUtil.verifyToken(token) < 0) {
            throw new ConditionException("非法用户id");
        }
        return TokenUtil.verifyToken(token);
    }
}
