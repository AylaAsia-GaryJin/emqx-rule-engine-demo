package com.ayla.emqxruleenginedemo.service;

import com.ayla.emqxruleenginedemo.entity.ActionResp;

import java.util.List;

public interface ActionService {
    List<ActionResp> searchAction();
    ActionResp getAction(String name);
}
