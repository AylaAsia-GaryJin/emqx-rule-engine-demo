package com.ayla.emqxruleenginedemo.service.impl;

import com.ayla.emqxruleenginedemo.entity.ActionResp;
import com.ayla.emqxruleenginedemo.entity.EmqxResp;
import com.ayla.emqxruleenginedemo.feign.EmqxFeignClient;
import com.ayla.emqxruleenginedemo.service.ActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ayla.emqxruleenginedemo.util.EmqxCallUtil.validCallerResult;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-06 16:33
 */
@Slf4j
@Service
public class ActionServiceImpl implements ActionService {
    @Autowired
    private EmqxFeignClient emqxFeignClient;
    @Override
    public List<ActionResp> searchAction() {
        EmqxResp<List<ActionResp>> resp = emqxFeignClient.searchAction();
        validCallerResult(resp, "search action error");
        return resp.getData();
    }

    @Override
    public ActionResp getAction(String name) {
        EmqxResp<ActionResp> resp = emqxFeignClient.getAction(name);
        validCallerResult(resp, "get action error");
        return resp.getData();
    }
}
