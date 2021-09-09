package com.ayla.emqxruleenginedemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-06 16:09
 */
@NoArgsConstructor
@Data
public class ResourceTypeResp {
    private TitleBean title;
    private String provider;
    private Map<String, Object> params;
    private String name;
    private DescriptionBean description;

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
