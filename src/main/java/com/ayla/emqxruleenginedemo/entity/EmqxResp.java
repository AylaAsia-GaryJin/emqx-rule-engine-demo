package com.ayla.emqxruleenginedemo.entity;

import lombok.Data;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-09-06 10:43
 */
@Data
public class EmqxResp<T> {
    private Integer code;
    private T data;
    private String message;
}
