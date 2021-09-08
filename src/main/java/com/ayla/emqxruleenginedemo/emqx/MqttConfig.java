package com.ayla.emqxruleenginedemo.emqx;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: emqx broker config
 * @author: Gary.Jin
 * @create: 2021-09-03 11:31
 */
@Getter
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {
    public static String dashboardUsername;

    public static String dashboardPassword;

    private String host;

    private int port;

    private int keepAlive;

    private int qos;

    private long sessionExpiryInterval;

    private boolean cleanStart;

    private String managementHost;

    private String clientUsername;

    private String clientPassword;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public void setSessionExpiryInterval(long sessionExpiryInterval) {
        this.sessionExpiryInterval = sessionExpiryInterval;
    }

    public void setCleanStart(boolean cleanStart) {
        this.cleanStart = cleanStart;
    }

    public void setDashboardUsername(String dashboardUsername) {
        MqttConfig.dashboardUsername = dashboardUsername;
    }

    public void setDashboardPassword(String dashboardPassword) {
        MqttConfig.dashboardPassword = dashboardPassword;
    }

    public void setManagementHost(String managementHost) {
        this.managementHost = managementHost;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
