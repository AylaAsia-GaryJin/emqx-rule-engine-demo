package com.ayla.emqxruleenginedemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-06 10:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmqxRuleEnableReq {
    private boolean enabled;
}
