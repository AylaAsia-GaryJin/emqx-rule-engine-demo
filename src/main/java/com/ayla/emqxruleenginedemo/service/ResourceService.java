package com.ayla.emqxruleenginedemo.service;

import com.ayla.emqxruleenginedemo.entity.ResourceReq;
import com.ayla.emqxruleenginedemo.entity.ResourceResp;
import com.ayla.emqxruleenginedemo.entity.ResourceTypeResp;
import com.ayla.emqxruleenginedemo.entity.ResourceWithObjectStatus;
import com.ayla.emqxruleenginedemo.entity.ResourceWithStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ResourceService {
    List<ResourceTypeResp> searchResourceType();

    ResourceTypeResp getResourceType(String name);

    ResourceResp saveResource(ResourceReq req);

    List<ResourceWithStatus> searchResources();

    ResourceWithObjectStatus getResource(String id);

    Integer deleteResource(String resourceId);
}
