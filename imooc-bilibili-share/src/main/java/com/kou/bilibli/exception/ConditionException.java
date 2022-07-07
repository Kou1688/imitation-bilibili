package com.kou.bilibli.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 条件异常类
 *
 * @author KouChaoJie
 * @since: 2022/7/7 14:16
 */
@Getter
@Setter
public class ConditionException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    private String code;

    public ConditionException(String code, String name) {
        super(name);
        this.code = code;
    }

    public ConditionException(String name) {
        super(name);
        code = "500";
    }
}
