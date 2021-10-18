package com.ayla.emqxruleenginedemo.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-10-16 12:42
 */
@FeignClient(url = "${mqtt.management_host}/api/v4", name = "cc-ruleengine", decode404 = true,
    configuration = {AylaCallerInterceptor.class})
public interface AylaFeignClient {
}
