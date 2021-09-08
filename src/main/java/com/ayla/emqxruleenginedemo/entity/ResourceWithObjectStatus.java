package com.ayla.emqxruleenginedemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-07 11:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceWithObjectStatus extends ResourceResp {
    private List<StatusBean> status;

    @Data
    public static class StatusBean {
        private String node;
        @JsonProperty("is_alive")
        private Boolean isAlive;
    }
}
