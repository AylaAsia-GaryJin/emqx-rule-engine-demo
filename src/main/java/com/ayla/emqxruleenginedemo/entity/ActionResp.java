package com.ayla.emqxruleenginedemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @description: emqx 定义的 action
 * @author: Gary.Jin
 * @create: 2021-09-06 16:24
 */
@NoArgsConstructor
@Data
public class ActionResp {
    private List<String> types;
    private TitleBean title;
    private String name;
    @JsonProperty("for")
    private String forX;
    private DescriptionBean description;
    private String category;
    private String app;
    private Map<String, Object> params;

    @NoArgsConstructor
    @Data
    public static class TitleBean {
        private String zh;
        private String en;
    }

    @NoArgsConstructor
    @Data
    public static class DescriptionBean {
        private String zh;
        private String en;
    }
}
