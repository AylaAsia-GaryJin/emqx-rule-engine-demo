package com.ayla.emqxruleenginedemo.service.impl;

import com.ayla.emqxruleenginedemo.entity.CloudCommonEvent;
import com.ayla.emqxruleenginedemo.entity.MessageRecord;
import com.ayla.emqxruleenginedemo.repository.MessageRecordRepository;
import com.ayla.emqxruleenginedemo.service.MessageService;
import com.ayla.emqxruleenginedemo.stream.StreamSink;
import com.aylaasia.corecloud.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-07 16:02
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRecordRepository messageRecordRepository;

    @Override
    public void saveMessage(CloudCommonEvent cloudCommonEvent) {
        log.info("receive message throw API: {}", cloudCommonEvent);
        MessageRecord messageRecord = new MessageRecord()
            .setMessageId(cloudCommonEvent.getEventId())
            .setMessageType(cloudCommonEvent.getEventType().getName())
            .setTopic(StreamSink.CONNECTIVITY_SINK)
            .setPayload(JsonUtil.toJson(cloudCommonEvent));
        messageRecordRepository.save(messageRecord);
    }
}
