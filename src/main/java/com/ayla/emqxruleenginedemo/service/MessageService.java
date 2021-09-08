package com.ayla.emqxruleenginedemo.service;

import com.ayla.emqxruleenginedemo.entity.CloudCommonEvent;

public interface MessageService {
    void saveMessage(CloudCommonEvent cloudCommonEvent);
}
