package com.ayla.emqxruleenginedemo.entity;

import com.ayla.emqxruleenginedemo.constant.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: kafka消息
 * @author: Gary.Jin
 * @create: 2021-09-02 15:10
 */
@Data
public class CloudCommonEvent {


    /**
     * 事件ID由iothub产生
     * mandatory
     */
    @JsonProperty("eventId")
    private String eventId;

    /**
     * 事件类型connectivity(设备上下线)
     */
    @JsonProperty("eventType")
    private Constant.EventsTypeEnum eventType;

    /**
     * ayla|ali
     * mandatory
     */
    @JsonProperty("platform")
    private String platform;

    /**
     * 设备唯一标识，艾拉为DSN，阿里为iotid
     * mandatory
     */
    @JsonProperty("deviceIdentifier")
    private String deviceIdentifier;

    /**
     * 区分设备对应的厂商,当platform=ayla时为必填，platform=ayla时可不填
     */
    @JsonProperty("oemId")
    private String oemId;

    /**
     * 设备品项，艾拉为oem_model,阿里为PK
     * mandatory
     */
    @JsonProperty("productModel")
    private String productModel;

    /**
     * iothub产生消息的时间，UTC时间？
     * mandatory
     */
    @JsonProperty("generateTime")
    private String generateTime;

    /**
     * 该原始消息产生的时间，UTC时间？
     * mandatory
     */
    @JsonProperty("time")
    private String time;

    /**
     * connectivity
     */
    @JsonProperty("connectivity")
    private Connectivity connectivity;

    /**
     * propertyPost
     */
    @JsonProperty("propertyPost")
    private PropertyPost propertyPost;

    /**
     * eventPost
     */
    @JsonProperty("eventPost")
    private EventPost eventPost;

    private int retryCount;

    /**
     * Item
     */
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @Data
    public static class Connectivity {

        /**
         * 最近上线时间(仅ali)UTC时间？
         * optional
         */
        @JsonProperty("lastTime")
        private String lastTime;

        /**
         * online|offline
         * mandatory
         */
        @JsonProperty("status")
        private String status;
    }

    /**
     * Item
     */
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @Data
    public static class PropertyPost {

        /**
         * 统一物模板的属性名mandatory
         */
        @JsonProperty("propertyName")
        private String propertyName;

        /**
         * 统一物模板转换后的属性值(jsonObjectType)
         * mandatory
         */
        @JsonProperty("propertyValue")
        private Object propertyValue;
    }

    /**
     * Item
     */
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @Data
    public static class EventPost {

        /**
         * mandatory事件的参数列表，size可以为0
         */
        @JsonProperty("parameters")
        private List<Parameters> parameters;

        /**
         * mandatory物模板中事件的标识符
         */
        @JsonProperty("eventIdentifier")
        private String eventIdentifier;

    }

    /**
     * mandatory事件的参数列表，size可以为0
     * Item
     */
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    @Data
    public static class Parameters {

        /**
         * parameterName
         */
        @JsonProperty("parameterName")
        private String parameterName;

        /**
         * parameterValue
         */
        @JsonProperty("parameterValue")
        private Object parameterValue;
    }
}
