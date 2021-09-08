package com.ayla.emqxruleenginedemo.kafka;

import com.ayla.emqxruleenginedemo.entity.CloudCommonEvent;
import com.ayla.emqxruleenginedemo.entity.MessageRecord;
import com.ayla.emqxruleenginedemo.repository.MessageRecordRepository;
import com.ayla.emqxruleenginedemo.stream.StreamSink;
import com.aylaasia.corecloud.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @description: 消费设备属性相关消息
 * @author: Gary.Jin
 * @create: 2021-09-02 15:06
 */
@Slf4j
@Service
public class PropertyConsumer {
    @Autowired
    private MessageRecordRepository messageRecordRepository;

    @StreamListener(StreamSink.PROPERTY_SINK)
    public void receive(Message<CloudCommonEvent> message) {
        log.info("receive property message from kafka: {}, header: {}", message.getPayload(), message.getHeaders());
        MessageRecord messageRecord = new MessageRecord()
            .setMessageId(message.getPayload().getEventId())
            .setMessageType("property")
            .setTopic(StreamSink.PROPERTY_SINK)
            .setPayload(JsonUtil.toJson(message.getPayload()));
        messageRecordRepository.save(messageRecord);

    }
}
