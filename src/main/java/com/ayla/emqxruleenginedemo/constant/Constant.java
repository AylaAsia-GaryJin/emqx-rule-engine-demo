package com.ayla.emqxruleenginedemo.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public interface Constant {
    @Getter
    enum EventsTypeEnum {

        /**
         * 上下线事件
         */
        CONNECTIVITY("connectivity"),
        /**
         * 属性上报
         */
        PROPERTY_POST("propertyPost"),

        /**
         * 事件上报
         */
        EVENT_POST("eventPost");

        @JsonValue
        private final String name;


        EventsTypeEnum(String name) {
            this.name = name;
        }

        @JsonCreator
        public static EventsTypeEnum fromName(String type) {
            for (EventsTypeEnum thisEnum : EventsTypeEnum.values()) {
                if (thisEnum.getName().equalsIgnoreCase(type)) {
                    return thisEnum;
                }
            }
            return null;
        }
    }

    class EmqxConstant {
        public static final Integer RESULT_CODE_OK = 0;
    }

    class RestResultMsg {
        public static final String OK = "success";
    }

    @Getter
    @AllArgsConstructor
    enum ResourceType {
        BACKEND_CASSA("backend_cassa", "Cassandra 数据库"),
        BACKEND_CLICKHOUSE("backend_clickhouse", "ClickHouse"),
        BACKEND_DOLPHINDB("backend_dolphindb", "DolphinDB 数据库"),
        BACKEND_INFLUXDB_HTTP("backend_influxdb_http", "使用 HTTP 协议将数据点写入 InfluxDB"),
        BACKEND_INFLUXDB_UDP("backend_influxdb_udp", "使用 UDP 协议将数据点写入 InfluxDB"),
        BACKEND_SQLSERVER("backend_sqlserver", "Microsoft SQL Server"),
        BACKEND_MONGO_RS("backend_mongo_rs", "MongoDB Relica Set 模式"),
        BACKEND_MONGO_SHARDED("backend_mongo_sharded", "MongoDB Shared 模式"),
        BACKEND_MONGO_SINGLE("backend_mongo_single", "MongoDB Single 模式"),
        BACKEND_MYSQL("backend_mysql", "MySQL 数据库"),
        BACKEND_OPENTSDB("backend_opentsdb", "OpenTSDB"),
        BACKEND_ORACLE("backend_oracle", "Oracle Database 数据库"),
        BACKEND_PGSQL("backend_pgsql", "PostgreSQL"),
        BACKEND_REDIS_CLUSTER("backend_redis_cluster", "Redis Cluster模式"),
        BACKEND_REDIS_SENTINEL("backend_redis_sentinel", "Redis Sentinel模式"),
        BACKEND_REDIS_SINGLE("backend_redis_single", "Redis Single模式"),
        BACKEND_TDENGINE("backend_tdengine", "TDengine"),
        BACKEND_TIMESCALE("backend_timescale", "TimescaleDB"),
        BRIDGE_RPC("bridge_rpc", "EMQ X RPC 消息桥接"),
        BRIDGE_KAFKA("bridge_kafka", "Kafka"),
        BRIDGE_MQTT("bridge_mqtt", "MQTT 消息桥接"),
        BRIDGE_RABBIT("bridge_rabbit", "RabbitMQ 资源"),
        BRIDGE_ROCKET("bridge_rocket", "RocketMQ"),
        BRIDGE_PULSAR("bridge_pulsar", "Pulsar"),
        PARSER_HTTP("parser_http", "HTTP 编解码"),
        PARSER_TCP("parser_tcp", "TCP 编解码"),
        WEB_HOOK("web_hook", "WebHook"),
        ;
        private String name;
        private String desc;
    }

    @Getter
    @AllArgsConstructor
    enum Action {
        data_to_kafka("桥接数据到 Kafka", List.of("bridge_kafka")),
        data_to_rabbit("桥接数据到 RabbitMQ", List.of("bridge_rabbit")),
        data_to_rocket("桥接数据到 RocketMQ", List.of("bridge_rocket")),
        data_to_cassa("保存数据到 Cassandra", List.of("backend_cassa")),
        data_to_clickhouse("保存数据到 ClickHouse 数据库", List.of("backend_clickhouse")),
        data_to_dolphindb("保存数据到 DolphinDB", List.of("backend_dolphindb")),
        data_to_dynamo("保存数据到 DynamoDB", List.of("backend_dynamo")),
        data_to_mysql("保存数据到 MySQL", List.of("backend_mysql")),
        data_to_pgsql("保存数据到 PostgreSQL", List.of("backend_pgsql")),
        data_to_sqlserver("保存数据到 SQLServer", List.of("backend_sqlserver")),
        data_to_tdengine("保存数据到 TDengine", List.of("backend_tdengine")),
        data_to_timescale("保存数据到 Timescale", List.of("backend_timescale")),
        data_to_webserver("发送数据到 Web 服务", List.of("web_hook")),
        data_to_oracle("保存数据到 Oracle Database 数据库", List.of("backend_oracle")),
        data_to_redis("保存数据到 Redis", List.of("backend_redis_single", "backend_redis_sentinel", "backend_redis_cluster")),
        data_to_opentsdb("保存数据到 OpenTSDB", List.of("backend_opentsdb")),
        data_to_influxdb("保存数据到 InfluxDB", List.of("backend_influxdb_udp", "backend_influxdb_http")),
        data_to_mongo("保存数据到 MongoDB", List.of("backend_mongo_single", "backend_mongo_sharded", "backend_mongo_rs")),
        data_to_mqtt_broker("桥接数据到 MQTT Broker", List.of("bridge_mqtt", "bridge_rpc")),
        data_to_pulsar("桥接数据到 Pulsar", List.of("bridge_pulsar")),

        inspect("检查 (调试)", List.of()),
        do_nothing("空动作 (调试)", List.of()),

        offline_msg_to_cassa("离线消息保存到 Cassandra", List.of("backend_cassa")),
        offline_msg_to_clickhouse("离线消息保存到 ClickHouse", List.of("backend_clickhouse")),
        offline_msg_to_mongo("离线消息保存到 MongoDB", List.of("backend_mongo_single", "backend_mongo_sharded", "backend_mongo_rs")),
        offline_msg_to_mysql("离线消息保存到 MySQL", List.of("backend_mysql")),
        offline_msg_to_pgsql("离线消息保存到 PostgreSQL", List.of("backend_pgsql")),
        offline_msg_to_redis("离线消息保存到 Redis", List.of("backend_redis_single", "backend_redis_sentinel", "backend_redis_cluster")),

        republish("重新发布消息到另一个主题", List.of()),

        lookup_sub_to_cassa("从 Cassandra 中获取订阅列表", List.of("backend_cassa")),
        lookup_sub_to_clickhouse("从 ClickHouse 中获取订阅列表", List.of("backend_clickhouse")),
        lookup_sub_to_mongo("从 MongoDB 中获取订阅列表", List.of("backend_mongo_single", "backend_mongo_sharded", "backend_mongo_rs")),
        lookup_sub_to_mysql("从 MySQL 中获取订阅列表", List.of("backend_mysql")),
        lookup_sub_to_pgsql("从 PostgreSQL 中获取订阅列表", List.of("backend_pgsql")),
        lookup_sub_to_redis("从 Redis 中获取订阅列表", List.of("backend_redis_single", "backend_redis_sentinel", "backend_redis_cluster")),

        ;
        private String desc;
        private List<String> type;
    }
}
