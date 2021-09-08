package com.ayla.emqxruleenginedemo.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @description: 规则引擎服务异常
 * @author: Gary.Jin
 * @create: 2021-09-06 10:28
 */
@Getter
@ToString
public class RuleEngineException extends RuntimeException {
    public static final int COMMON_ERROR_CODE = 400;
    private final Integer code;
    private final String message;

    public RuleEngineException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public RuleEngineException(String message) {
        super(message);
        this.message = message;
        this.code = COMMON_ERROR_CODE;
    }
}
