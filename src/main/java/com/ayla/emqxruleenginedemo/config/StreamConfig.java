package com.ayla.emqxruleenginedemo.config;

import com.ayla.emqxruleenginedemo.stream.StreamSink;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-02 14:45
 */
@EnableBinding({StreamSink.class})
public class StreamConfig {
}
