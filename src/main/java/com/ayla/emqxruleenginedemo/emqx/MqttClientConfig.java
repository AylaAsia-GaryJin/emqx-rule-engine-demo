package com.ayla.emqxruleenginedemo.emqx;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;
import com.hivemq.client.mqtt.mqtt5.message.connect.Mqtt5Connect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description: mqtt client config
 * @author: Gary.Jin
 * @create: 2021-09-03 11:22
 */
@Slf4j
@Configuration
public class MqttClientConfig {

    @Bean
    public Mqtt5AsyncClient mqtt5AsyncClient(MqttConfig mqttConfig) {
        final Mqtt5AsyncClient mqtt5AsyncClient = createMqtt5Client(mqttConfig).toAsync();
        mqtt5AsyncClient.connect(createMqtt5Connect(mqttConfig));
        log.info("cc-rule-engine connected MQTT broker as AsyncClient, with config: {}", mqttConfig);
        return mqtt5AsyncClient;
    }

    private Mqtt5Client createMqtt5Client(MqttConfig mqttConfig) {
        final String clientId = "cc-rule-engine-" + UUID.randomUUID();
        return MqttClient.builder()
            .useMqttVersion5()
            .identifier(clientId)
            .serverHost(mqttConfig.getHost())
            .serverPort(mqttConfig.getPort())
            .simpleAuth()
            .username(mqttConfig.getClientUsername())
            .password(mqttConfig.getClientPassword().getBytes(StandardCharsets.UTF_8))
            .applySimpleAuth()
            .automaticReconnect()
            .initialDelay(1, TimeUnit.SECONDS)
            .maxDelay(1, TimeUnit.MINUTES)
            .applyAutomaticReconnect()
            .build();
    }

    private Mqtt5Connect createMqtt5Connect(MqttConfig mqttConfig) {
        return Mqtt5Connect.builder()
            .cleanStart(mqttConfig.isCleanStart())
            .sessionExpiryInterval(mqttConfig.getSessionExpiryInterval())
            .keepAlive(mqttConfig.getKeepAlive())
            .build();
    }

}
