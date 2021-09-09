package com.ayla.emqxruleenginedemo.feign;

import com.ayla.emqxruleenginedemo.entity.ActionResp;
import com.ayla.emqxruleenginedemo.entity.EmqxResp;
import com.ayla.emqxruleenginedemo.entity.EmqxRuleEnableReq;
import com.ayla.emqxruleenginedemo.entity.ResourceReq;
import com.ayla.emqxruleenginedemo.entity.ResourceResp;
import com.ayla.emqxruleenginedemo.entity.ResourceTypeResp;
import com.ayla.emqxruleenginedemo.entity.ResourceWithObjectStatus;
import com.ayla.emqxruleenginedemo.entity.ResourceWithStatus;
import com.ayla.emqxruleenginedemo.entity.Rule;
import com.ayla.emqxruleenginedemo.entity.RuleResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "${mqtt.management_host}/api/v4", name = "cc-ruleengine", decode404 = true,
    configuration = {EmqxCallerInterceptor.class})
public interface EmqxFeignClient {
    @PostMapping("/rules")
    EmqxResp<RuleResp> createRule(@RequestBody Rule rule);

    @GetMapping("/rules/{ruleId}")
    EmqxResp<RuleResp> getById(@PathVariable("ruleId") String ruleId);

    @DeleteMapping("/rules/{ruleId}")
    EmqxResp deleteRuleById(@PathVariable("ruleId") String ruleId);

    @GetMapping("/rules")
    EmqxResp<List<RuleResp>> searchAllRule();

    @PutMapping("/rules/{ruleId}")
    EmqxResp<RuleResp> changeRuleStatus(@PathVariable("ruleId") String ruleId, @RequestBody EmqxRuleEnableReq req);

    @GetMapping("/resource_types")
    EmqxResp<List<ResourceTypeResp>> searchResourceType();

    @GetMapping("/resource_types/{name}")
    EmqxResp<ResourceTypeResp> getResourceType(@PathVariable("name") String name);

    @PostMapping("/resources")
    EmqxResp<ResourceResp> saveResource(ResourceReq req);

    @GetMapping("/resources")
    EmqxResp<List<ResourceWithStatus>> searchResources();

    @GetMapping("/resources/{id}")
    EmqxResp<ResourceWithObjectStatus> getResource(@PathVariable("id") String id);

    @DeleteMapping("/resources/{id}")
    EmqxResp deleteResource(@PathVariable("id") String id);

    @GetMapping("/actions")
    EmqxResp<List<ActionResp>> searchAction();

    @GetMapping("/actions/{name}")
    EmqxResp<ActionResp> getAction(@PathVariable("name") String name);
}
