package com.kou.bilibili.service.hanlder;

import com.kou.bilibili.domian.JsonResponse;
import com.kou.bilibli.exception.ConditionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author KouChaoJie
 * @since: 2022/7/7 14:14
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonGlobalExceptionHandler {
    /**
     * 全局异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonResponse<String> conditionExceptionHandler(HttpServletRequest request, Exception e) {
        String errorMsg = e.getMessage();
        if (e instanceof ConditionException) {
            return JsonResponse.fail(((ConditionException) e).getCode(), errorMsg);
        } else {
            return JsonResponse.fail("500",errorMsg);
        }
    }
}
