package com.ayla.emqxruleenginedemo.controller;

import com.ayla.emqxruleenginedemo.entity.ActionResp;
import com.ayla.emqxruleenginedemo.service.ActionService;
import com.aylaasia.corecloud.common.pojo.RestResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 动作相关 API
 * @author: Gary.Jin
 * @create: 2021-09-02 15:17
 */
@RequestMapping("/actions")
@RestController
public class ActionController {
    @Autowired
    private ActionService actionService;

    @GetMapping("")
    public RestResponseBody<List<ActionResp>> searchAction() {
        return RestResponseBody.success(actionService.searchAction());
    }

    @GetMapping("/{actionName}")
    public RestResponseBody<ActionResp> getAction(@PathVariable String actionName) {
        return RestResponseBody.success(actionService.getAction(actionName));
    }
}
