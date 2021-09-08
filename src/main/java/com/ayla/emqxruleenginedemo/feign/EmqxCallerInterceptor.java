package com.ayla.emqxruleenginedemo.feign;

import com.alibaba.nacos.client.identify.Base64;
import com.ayla.emqxruleenginedemo.emqx.MqttConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @description: call emqx 管理 API 拦截器
 * @author: Gary.Jin
 * @create: 2021-09-06 11:23
 */
public class EmqxCallerInterceptor implements RequestInterceptor {
    public static final String EMQX_HEADER_KEY = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        String header =
            this.createBasicAuthHeaders(MqttConfig.dashboardUsername, MqttConfig.dashboardPassword);
        template.header(EMQX_HEADER_KEY, header);
    }

    public String createBasicAuthHeaders(final String username, final String password) {
        final String auth = username + ":" + password;
        final byte[] encodedAuth = Base64.encodeBase64(
            auth.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + new String(encodedAuth);
    }
}
