package com.ayla.emqxruleenginedemo.rocketmq;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.remoting.common.RemotingHelper;
import com.ayla.emqxruleenginedemo.entity.MessageRecord;
import com.ayla.emqxruleenginedemo.repository.MessageRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StatusRocketMqListener implements MessageListener {
    public static final int RECONSUME_TIMES_MAX = 2;

    @Autowired
    private MessageRecordRepository messageRecordRepository;

    @Override
    public Action consume(Message message, ConsumeContext context) {
        try {
            String msgKey = message.getKey();
            String msgId = message.getMsgID();
            long bornTimestamp = message.getBornTimestamp();
            byte[] body = message.getBody();
            String msg = new String(body, RemotingHelper.DEFAULT_CHARSET);
            log.info("Receive status message by msgID: {},msgKey:{}，bornTimestamp: {}，message: {}，msgBody: {}",
                msgId, msgKey, bornTimestamp, message, msg);
            MessageRecord messageRecord = new MessageRecord()
                .setMessageId(msgId)
                .setMessageType("rockemq_status")
                .setTopic(message.getTopic())
                .setPayload(msg);
            messageRecordRepository.save(messageRecord);
            return Action.CommitMessage;
        } catch (Exception e) {
            log.error("Ayla property message processing failed", e);
            if (message.getReconsumeTimes() <= RECONSUME_TIMES_MAX) {

                return Action.ReconsumeLater;
            }
            return Action.CommitMessage;
        }
    }

}
