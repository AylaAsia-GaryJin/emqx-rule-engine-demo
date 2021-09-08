package com.ayla.emqxruleenginedemo.emqx;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5PublishResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @description: 发布消息到 EMQ X
 * @author: Gary.Jin
 * @create: 2021-09-03 11:50
 */
@Service
public class MqttPublisher {
    @Autowired
    private Mqtt5AsyncClient mqtt5AsyncClient;
    @Autowired
    private MqttConfig mqttConfig;

    public CompletableFuture<Mqtt5PublishResult> publishMessage(final String topic, final String payload,
        final long messageExpirySeconds, int qos) {
        var mqttQos = MqttQos.fromCode(qos);
        if (mqttQos == null) {
            mqttQos = MqttQos.AT_LEAST_ONCE;
        }
        return mqtt5AsyncClient
            .publishWith()
            .topic(topic)
            .messageExpiryInterval(messageExpirySeconds)
            .payload(payload.getBytes())
            .qos(mqttQos)
            .send();
    }

    public CompletableFuture<Mqtt5PublishResult> publishMessage(String topic, String payload) {
        return publishMessage(topic, payload, mqttConfig.getSessionExpiryInterval(), mqttConfig.getQos());
    }
}
