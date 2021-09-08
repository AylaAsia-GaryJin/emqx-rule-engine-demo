package com.ayla.emqxruleenginedemo.service.impl;

import com.ayla.emqxruleenginedemo.entity.EmqxResp;
import com.ayla.emqxruleenginedemo.entity.ResourceReq;
import com.ayla.emqxruleenginedemo.entity.ResourceResp;
import com.ayla.emqxruleenginedemo.entity.ResourceTypeResp;
import com.ayla.emqxruleenginedemo.entity.ResourceWithObjectStatus;
import com.ayla.emqxruleenginedemo.entity.ResourceWithStatus;
import com.ayla.emqxruleenginedemo.feign.EmqxFeignClient;
import com.ayla.emqxruleenginedemo.service.ResourceService;
import com.ayla.emqxruleenginedemo.util.EmqxCallUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.ayla.emqxruleenginedemo.util.EmqxCallUtil.validCallerResult;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-06 16:06
 */
@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private EmqxFeignClient emqxFeignClient;

    @Override
    public List<ResourceTypeResp> searchResourceType() {
        EmqxResp<List<ResourceTypeResp>> resp = emqxFeignClient.searchResourceType();
        validCallerResult(resp, "search resource type error");
        return resp.getData();
    }

    @Override
    public ResourceTypeResp getResourceType(String name) {
        EmqxResp<ResourceTypeResp> resp = emqxFeignClient.getResourceType(name);
        validCallerResult(resp, "get resource type error");
        return resp.getData();
    }

    @Override
    public ResourceResp saveResource(ResourceReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            req.setId(EmqxCallUtil.resourceId());
        }
        EmqxResp<ResourceResp> resp = emqxFeignClient.saveResource(req);
        validCallerResult(resp, "save resource error");
        return resp.getData();
    }

    @Override
    public List<ResourceWithStatus> searchResources() {
        EmqxResp<List<ResourceWithStatus>> resp = emqxFeignClient.searchResources();
        validCallerResult(resp, "search resources error");
        return resp.getData();
    }

    @Override
    public ResourceWithObjectStatus getResource(String id) {
        EmqxResp<ResourceWithObjectStatus> resp = emqxFeignClient.getResource(id);
        validCallerResult(resp, "get resource by id error");
        return resp.getData();
    }

    @Override
    public Integer deleteResource(String resourceId) {
        EmqxResp resp = emqxFeignClient.deleteResource(resourceId);
        validCallerResult(resp, "delete resource error");
        return 1;
    }
}
