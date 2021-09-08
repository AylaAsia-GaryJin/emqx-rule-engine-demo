package com.ayla.emqxruleenginedemo.service.impl;

import com.ayla.emqxruleenginedemo.constant.Constant;
import com.ayla.emqxruleenginedemo.entity.EmqxResp;
import com.ayla.emqxruleenginedemo.entity.EmqxRuleEnableReq;
import com.ayla.emqxruleenginedemo.entity.Rule;
import com.ayla.emqxruleenginedemo.entity.RuleResp;
import com.ayla.emqxruleenginedemo.exception.RuleEngineException;
import com.ayla.emqxruleenginedemo.feign.EmqxFeignClient;
import com.ayla.emqxruleenginedemo.service.RuleService;
import com.ayla.emqxruleenginedemo.util.EmqxCallUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.ayla.emqxruleenginedemo.util.EmqxCallUtil.validCallerResult;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-03 18:07
 */
@Slf4j
@Service
public class RuleServiceImpl implements RuleService {
    public static final String TENANT_ID = "'tid1'";
    @Autowired
    private EmqxFeignClient emqxFeignClient;

    @Override
    public RuleResp saveRule(Rule rule) {
        if (ObjectUtils.isEmpty(rule.getId())) {
            rule.setId(EmqxCallUtil.ruleId());
        }
        String rawsql = rule.getRawsql().replaceAll(";", "");
        if (rawsql.toLowerCase().contains("where")) {
            rawsql = rawsql + " and payload.tenantId=" + TENANT_ID;
        } else {
            rawsql = rawsql + " where payload.tenantId=" + TENANT_ID;
        }
        rule.setRawsql(rawsql);
        EmqxResp<RuleResp> result = emqxFeignClient.createRule(rule);
        validCallerResult(result, "save rule not success");
        return result.getData();
    }

    @Override
    public RuleResp getRule(String id) {
        EmqxResp<RuleResp> result = emqxFeignClient.getById(id);
        validCallerResult(result, "get rule not success");
        return result.getData();
    }

    @Override
    public int deleteRule(String id) {
        EmqxResp emqxResp = emqxFeignClient.deleteRuleById(id);
        validCallerResult(emqxResp, "delete rule failed");
        return 1;
    }

    @Override
    public List<RuleResp> searchRule() {
        EmqxResp<List<RuleResp>> result = emqxFeignClient.searchAllRule();
        validCallerResult(result, "search rule not success");
        return result.getData();
    }

    @Override
    public RuleResp changeStatus(boolean enable, String id) {
        EmqxRuleEnableReq req = new EmqxRuleEnableReq(enable);
        EmqxResp<RuleResp> resp = emqxFeignClient.changeRuleStatus(id, req);
        validCallerResult(resp, "change rule status failed");
        return resp.getData();
    }
}
