package com.ayla.emqxruleenginedemo.util;

import com.ayla.emqxruleenginedemo.constant.Constant;
import com.ayla.emqxruleenginedemo.entity.EmqxResp;
import com.ayla.emqxruleenginedemo.exception.RuleEngineException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-06 10:51
 */
@Slf4j
public class EmqxCallUtil {
    /**
     * rule id 以"rule_"开头。
     * note：仅限数字、字母、下划线和冒号。官方的例子中是以"rule:"开头的，但是 url-encode 发送之后，emqx 服务器不会decode，所以会有问题
     */
    public static final String RULE_ID_PREFIX = "rule_";
    public static final String RESOURCE_ID_PREFIX = "resource_";
    private EmqxCallUtil() {
    }

    public static <T> void validCallerResult(EmqxResp<T> emqxResp, String errorMessage) {
        log.info("emqx call result: {}", emqxResp);
        if (!Constant.EmqxConstant.RESULT_CODE_OK.equals(emqxResp.getCode())) {
            throw new RuleEngineException(errorMessage + ":" + emqxResp.getMessage());
        }
    }

    public static String ruleId() {
        return RULE_ID_PREFIX + UUID.randomUUID().toString().replace("-", "");
    }
    public static String resourceId() {
        return RESOURCE_ID_PREFIX + UUID.randomUUID().toString().replace("-", "");
    }

}
