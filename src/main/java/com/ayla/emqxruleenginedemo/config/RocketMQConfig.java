package com.ayla.emqxruleenginedemo.config;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.ayla.emqxruleenginedemo.rocketmq.EventRocketMqListener;
import com.ayla.emqxruleenginedemo.rocketmq.PropertyRocketMqListener;
import com.ayla.emqxruleenginedemo.rocketmq.StatusRocketMqListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-07 14:23
 */
@Configuration
public class RocketMQConfig {
    public static final Integer INITIAL_CAPACITY = 16;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean propertyRocketMqConsumer(AliConfig aliConfig,
        PropertyRocketMqListener propertyRocketMqListener) {
        ConsumerBean consumerBean = new ConsumerBean();
        Properties properties = aliConfig.getAliMqProperties();
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR,
            aliConfig.getRocketMq().getAliIotTopic().getNameSrvAddr());
        properties.setProperty(PropertyKeyConst.GROUP_ID, aliConfig.getRocketMq().getAliIotTopic().getMsgGroupId());
        consumerBean.setProperties(properties);
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>(INITIAL_CAPACITY);
        Subscription subscription = new Subscription();
        subscription.setTopic(aliConfig.getRocketMq().getAliIotTopic().getMsgTopic());
        subscriptionTable.put(subscription, propertyRocketMqListener);
        consumerBean.setSubscriptionTable(subscriptionTable);
        return consumerBean;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean statusRocketMqConsumer(AliConfig aliConfig,
        StatusRocketMqListener statusRocketMqListener) {
        ConsumerBean consumerBean = new ConsumerBean();
        Properties properties = aliConfig.getAliMqProperties();
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR,
            aliConfig.getRocketMq().getAliIotTopic().getNameSrvAddr());
        properties.setProperty(PropertyKeyConst.GROUP_ID, aliConfig.getRocketMq().getAliIotTopic().getStatusGroupId());
        consumerBean.setProperties(properties);
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>(INITIAL_CAPACITY);
        Subscription subscription = new Subscription();
        subscription.setTopic(aliConfig.getRocketMq().getAliIotTopic().getStatusTopic());
        subscriptionTable.put(subscription, statusRocketMqListener);
        consumerBean.setSubscriptionTable(subscriptionTable);
        return consumerBean;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean eventRocketMqConsumer(AliConfig aliConfig,
        EventRocketMqListener eventRocketMqListener) {
        ConsumerBean consumerBean = new ConsumerBean();
        Properties properties = aliConfig.getAliMqProperties();
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR,
            aliConfig.getRocketMq().getAliIotTopic().getNameSrvAddr());
        properties.setProperty(PropertyKeyConst.GROUP_ID, aliConfig.getRocketMq().getAliIotTopic().getEventGroupId());
        consumerBean.setProperties(properties);
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>(INITIAL_CAPACITY);
        Subscription subscription = new Subscription();
        subscription.setTopic(aliConfig.getRocketMq().getAliIotTopic().getEventTopic());
        subscriptionTable.put(subscription, eventRocketMqListener);
        consumerBean.setSubscriptionTable(subscriptionTable);
        return consumerBean;
    }

}
