package com.ayla.emqxruleenginedemo.controller;

import com.ayla.emqxruleenginedemo.entity.CloudCommonEvent;
import com.ayla.emqxruleenginedemo.service.MessageService;
import com.aylaasia.corecloud.common.pojo.RestResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 接收桥接的 Message
 * @author: Gary.Jin
 * @create: 2021-09-03 17:36
 */
@RequestMapping("/messages")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @PostMapping("")
    public RestResponseBody receiveMsgFromEmqx(@RequestBody CloudCommonEvent cloudCommonEvent) {
        messageService.saveMessage(cloudCommonEvent);
        return RestResponseBody.success();
    }
}
