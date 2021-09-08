ALTER TABLE `message_records`
    ADD COLUMN `topic` varchar(255) NOT NULL COMMENT '消息来源 topic' AFTER `message_id`;