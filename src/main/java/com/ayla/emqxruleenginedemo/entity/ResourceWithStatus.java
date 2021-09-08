package com.ayla.emqxruleenginedemo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-07 11:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceWithStatus extends ResourceResp {
    private Boolean status;
}
