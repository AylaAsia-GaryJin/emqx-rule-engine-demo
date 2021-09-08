package com.ayla.emqxruleenginedemo.controller;

import com.ayla.emqxruleenginedemo.entity.ResourceReq;
import com.ayla.emqxruleenginedemo.entity.ResourceResp;
import com.ayla.emqxruleenginedemo.entity.ResourceTypeResp;
import com.ayla.emqxruleenginedemo.entity.ResourceWithObjectStatus;
import com.ayla.emqxruleenginedemo.entity.ResourceWithStatus;
import com.ayla.emqxruleenginedemo.service.ResourceService;
import com.aylaasia.corecloud.common.pojo.RestResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 资源管理相关 API
 * @author: Gary.Jin
 * @create: 2021-09-02 15:16
 */
@RestController
@RequestMapping("/resources")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @GetMapping("/types")
    public RestResponseBody<List<ResourceTypeResp>> searchResourceType() {
        return RestResponseBody.success(resourceService.searchResourceType());
    }

    @GetMapping("/types/{name}")
    public RestResponseBody<ResourceTypeResp> getResourceType(@PathVariable String name) {
        return RestResponseBody.success(resourceService.getResourceType(name));
    }

    @PostMapping("")
    public RestResponseBody<ResourceResp> saveResource(@RequestBody ResourceReq req) {
        return RestResponseBody.success(resourceService.saveResource(req));
    }

    @GetMapping("")
    public RestResponseBody<List<ResourceWithStatus>> searchResources() {
        return RestResponseBody.success(resourceService.searchResources());
    }

    @GetMapping("/{id}")
    public RestResponseBody<ResourceWithObjectStatus> getResource(@PathVariable String id) {
        return RestResponseBody.success(resourceService.getResource(id));
    }

    @DeleteMapping("/{resourceId}")
    public RestResponseBody<Integer> deleteResource(@PathVariable String resourceId) {
        return RestResponseBody.success(resourceService.deleteResource(resourceId));
    }
}
