package com.ayla.emqxruleenginedemo.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 规则
 * @author: Gary.Jin
 * @create: 2021-09-03 17:56
 */
@Data
public class Rule {

    /**
     * rawsql : SELECT payload.msg as msg FROM "t/#" WHERE msg = 'hello'
     * actions : [{"name":"data_to_kafka","params":{"topic":"rule_engine_test","type":"async","strategy":"random","key":"none","required_acks":"all_isr","partition_count_refresh_interval":"60s","cache_mode":"Memory","max_total_bytes":"2GB","segments_bytes":"100MB","payload_tmpl":"","$resource":"resource:826285"}}]
     * description : 桥接到 cc-dev kafka
     * id : rule:305470
     */

    @NotBlank
    private String rawsql;
    private String description;
    private String id;
    @NotEmpty
    @Valid
    private List<ActionBean> actions;

    @Data
    public static class ActionBean {
        @NotBlank
        private String name;
        private List<ActionBean> fallbacks = new ArrayList<>();
        private Map<String, Object> params;
    }
}
