package com.ayla.emqxruleenginedemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 从 EMQ X 规则引擎转发来的消息格式
 * {
 *     "username": "user",
 *     "topic": "cc/conn/",
 *     "timestamp": 1630923238521,
 *     "qos": 0,
 *     "publish_received_at": 1630923238521,
 *     "pub_props": {},
 *     "peerhost": "182.150.24.34",
 *     "payload": "{\n    \"eventId\": \"1544343534234\", \n    \"tenant_id\": \"tid1\",\n    \"eventType\": \"connectivity\",\n    \"platform\": \"ayla\", \n    \"deviceIdentifier\": \"4z819VQHk6VSLmmBJfrf00107e\", \n    \"oemId\": \"0dfc7900\",\n    \"productModel\": \"tv\", \n    \"generateTime\": \"2018-08-31T07:32:28.205Z\", \n    \"time\": \"2018-08-31T07:32:28.205Z\", \n    \"connectivity\": {\n        \"lastTime\": \"2018-08-31T07:32:28.205Z\", \n        \"status\": \"online\" \n    } \n}",
 *     "node": "7379d6cddb74@172.17.0.4",
 *     "metadata": {"rule_id": "rule_5c16acb5a15e4b09b58baad2685cd261"},
 *     "id": "0005CB50E849B9C7F21A01006A7A0003",
 *     "headers": {
 *         "username": "user",
 *         "protocol": "mqtt",
 *         "proto_ver": 4,
 *         "properties": {},
 *         "peerhost": "182.150.24.34"
 *     },
 *     "flags": {
 *         "retain": false,
 *         "dup": false
 *     },
 *     "event": "message.publish",
 *     "clientid": "mqttx_5b179bc2"
 * }
 * @author: Gary.Jin
 * @create: 2021-09-07 10:35
 */
@NoArgsConstructor
@Data
public class EmqxRuleEngineMessage {
    private String username;
    private String topic;
    private Long timestamp;
    private Integer qos;
    @JsonProperty("publish_received_at")
    private Long publishReceivedAt;
    @JsonProperty("pub_props")
    private PubPropsBean pubProps;
    @JsonProperty("peerhost")
    private String peerHost;
    private String payload;
    private String node;
    private MetadataBean metadata;
    private String id;
    private HeadersBean headers;
    private FlagsBean flags;
    private String event;
    @JsonProperty("clientid")
    private String clientId;

    @NoArgsConstructor
    @Data
    public static class PubPropsBean {
    }

    @NoArgsConstructor
    @Data
    public static class MetadataBean {
        private String rule_id;
    }

    @NoArgsConstructor
    @Data
    public static class HeadersBean {
        private String username;
        private String protocol;
        private int proto_ver;
        private PropertiesBean properties;
        private String peerhost;

        @NoArgsConstructor
        @Data
        public static class PropertiesBean {
        }
    }

    @NoArgsConstructor
    @Data
    public static class FlagsBean {
        private boolean retain;
        private boolean dup;
    }
}
