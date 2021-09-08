package com.ayla.emqxruleenginedemo.service;

import com.ayla.emqxruleenginedemo.entity.Rule;
import com.ayla.emqxruleenginedemo.entity.RuleResp;

import java.util.List;

public interface RuleService {
    RuleResp saveRule(Rule rule);

    RuleResp getRule(String id);

    int deleteRule(String id);

    List<RuleResp> searchRule();

    RuleResp changeStatus(boolean enable, String id);
}
