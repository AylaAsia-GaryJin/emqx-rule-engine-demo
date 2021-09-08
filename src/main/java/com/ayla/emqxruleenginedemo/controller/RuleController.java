package com.ayla.emqxruleenginedemo.controller;

import com.ayla.emqxruleenginedemo.entity.Rule;
import com.ayla.emqxruleenginedemo.entity.RuleResp;
import com.ayla.emqxruleenginedemo.service.RuleService;
import com.aylaasia.corecloud.common.pojo.RestResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: rule 管理相关 API
 * @author: Gary.Jin
 * @create: 2021-09-02 15:13
 */
@RestController
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;
    @PostMapping("")
    public RestResponseBody<RuleResp> saveRule(@RequestBody @Valid Rule rule) {
        return RestResponseBody.success(ruleService.saveRule(rule));
    }

    @GetMapping("/{id}")
    public RestResponseBody<RuleResp> getRule(@PathVariable String id) {
        return RestResponseBody.success(ruleService.getRule(id));
    }

    @DeleteMapping("/{id}")
    public RestResponseBody<Integer> deleteRule(@PathVariable String id) {
        return RestResponseBody.success(ruleService.deleteRule(id));
    }

    @GetMapping("")
    public RestResponseBody<List<RuleResp>> searchRule() {
        return RestResponseBody.success(ruleService.searchRule());
    }

    @PutMapping("/{id}/status")
    public RestResponseBody<RuleResp> changeStatus(@RequestParam @NotNull Boolean enabled, @PathVariable String id) {
        return RestResponseBody.success(ruleService.changeStatus(enabled, id));
    }
}
