package com.ayla.emqxruleenginedemo.kafka;

import com.ayla.emqxruleenginedemo.entity.CloudCommonEvent;
import com.ayla.emqxruleenginedemo.entity.EmqxRuleEngineMessage;
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
 * @description: 消费设备事件相关消息
 * @author: Gary.Jin
 * @create: 2021-09-02 15:06
 */
@Slf4j
@Service
public class EventConsumer {
    @Autowired
    private MessageRecordRepository messageRecordRepository;

    @StreamListener(StreamSink.EVENT_SINK)
    public void receive(Message<EmqxRuleEngineMessage> message) {
        log.info("receive event message from kafka: {}, header: {}", message.getPayload(), message.getHeaders());
        CloudCommonEvent cloudCommonEvent =
            JsonUtil.fromJson(message.getPayload().getPayload(), CloudCommonEvent.class);

        MessageRecord messageRecord = new MessageRecord()
            .setMessageId(cloudCommonEvent.getEventId())
            .setMessageType("event")
            .setTopic(StreamSink.EVENT_SINK)
            .setPayload(JsonUtil.toJson(message.getPayload()));
        messageRecordRepository.save(messageRecord);

    }
}
