package com.ayla.emqxruleenginedemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-06 15:51
 */
@NoArgsConstructor
@Data
public class ResourceReq {
    private Map<String, Object> config;
    private String description;
    @NotBlank
    private String type;
    private String id;
    private String name;
}
