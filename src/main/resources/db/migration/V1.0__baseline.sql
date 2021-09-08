CREATE TABLE `message_records`
(
    `id`           bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `message_id`   varchar(255)        NULL COMMENT '消息ID',
    `payload`      varchar(5000)       NOT NULL COMMENT '消息体',
    `message_type` varchar(50)         NOT NULL COMMENT '消息类型',
    `create_at`    timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`    timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX (`message_id`)
) COMMENT = '消息记录表';

CREATE TABLE `resource_types`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(255)        NOT NULL COMMENT '资源类型名称',
    `title`       varchar(255)        NULL COMMENT '资源类型标题',
    `provider`    varchar(255)        NULL COMMENT '资源类型提供方',
    `description` varchar(255)        NULL COMMENT '资源类型描述',
    `raw_json`    json                NULL COMMENT '资源类型 json',
    `create_at`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX (`name`)
) COMMENT = '资源类型表';

