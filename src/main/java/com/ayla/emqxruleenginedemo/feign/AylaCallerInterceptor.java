package com.ayla.emqxruleenginedemo.feign;

import com.alibaba.nacos.client.identify.Base64;
import com.ayla.emqxruleenginedemo.emqx.MqttConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @description: call ayla 管理 API 拦截器
 * @author: Gary.Jin
 * @create: 2021-09-06 11:23
 */
public class AylaCallerInterceptor implements RequestInterceptor {
    public static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        template.header(AUTHORIZATION, "auth_token 49014b37aa3c432780f6832ba998787d");
    }
}
