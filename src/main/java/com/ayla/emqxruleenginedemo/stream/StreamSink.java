package com.ayla.emqxruleenginedemo.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamSink {
    String EVENT_SINK = "event-topic-test";
    String CONNECTIVITY_SINK = "connectivity-topic-test";
    String PROPERTY_SINK = "property-topic-test";

    @Input(EVENT_SINK)
    SubscribableChannel getEventChannel();

    @Input(CONNECTIVITY_SINK)
    SubscribableChannel getConnectivityChannel();

    @Input(PROPERTY_SINK)
    SubscribableChannel getPropertyChannel();
}
