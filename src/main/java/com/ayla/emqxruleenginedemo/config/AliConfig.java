package com.ayla.emqxruleenginedemo.config;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * @ClassName AliRocketMQConfig
 * @Description 阿里云RocketMQs实例配置类
 * @Author Tyler
 * @Date 2020/6/4 10:25
 **/
@Configuration
@ConfigurationProperties(prefix = "ali")
@Data
@Slf4j
public class AliConfig {

    private String accessKey;
    private String secretKey;
    private RocketMqConfig rocketMq;
    private AliIotClientConfig iotClient;

    @Data
    public static class RocketMqConfig {
        private IotTopic aliIotTopic;
        private IotTopic aylaIotTopic;
    }

    @Data
    public static class IotTopic {
        private String nameSrvAddr;
        private String msgTopic;
        private String msgGroupId;
        private String statusTopic;
        private String statusGroupId;
        private String eventTopic;
        private String eventGroupId;
    }


    @Data
    public static class AliIotClientConfig {
        private String regionId;
        private String domain;
        private String version;
    }

    public Properties getAliMqProperties() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, this.accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, this.secretKey);
        return properties;
    }
}
