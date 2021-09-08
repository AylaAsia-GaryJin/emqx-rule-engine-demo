package com.ayla.emqxruleenginedemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-03 15:59
 */
@Configuration
public class LogConfig {
    private static final int DEFAULT_MAX_PAYLOAD_LENGTH = 2000;

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(DEFAULT_MAX_PAYLOAD_LENGTH);
        return loggingFilter;
    }
}
