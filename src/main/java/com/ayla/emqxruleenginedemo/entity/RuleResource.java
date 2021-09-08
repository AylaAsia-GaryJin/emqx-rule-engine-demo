package com.ayla.emqxruleenginedemo.entity;

import lombok.Data;

import java.util.Map;

/**
 * @description: 资源
 * @author: Gary.Jin
 * @create: 2021-09-03 10:39
 */
@Data
public class RuleResource {

    /**
     * config : {"servers":"106.14.31.32:9092","min_metadata_refresh_interval":"3s","username":"","password":"","sync_timeout":"3s","max_batch_bytes":"900KB","compression":"no_compression","send_buffer":"1024KB","query_api_versions":true,"ssl":false,"cacertfile":{"file":"","filename":""},"keyfile":{"file":"","filename":""},"certfile":{"file":"","filename":""},"verify":false}
     * description : 核心云开发环境 kafka
     * type : bridge_kafka
     * id : resource:647801
     * name : bridge_kafka
     */

    private Map<String, Object> config;
    private String description;
    private String type;
    private String id;
    private String name;

    @Data
    public static class KafkaConfigBean {
        /**
         * servers : 106.14.31.32:9092
         * min_metadata_refresh_interval : 3s
         * username :
         * password :
         * sync_timeout : 3s
         * max_batch_bytes : 900KB
         * compression : no_compression
         * send_buffer : 1024KB
         * query_api_versions : true
         * ssl : false
         * cacertfile : {"file":"","filename":""}
         * keyfile : {"file":"","filename":""}
         * certfile : {"file":"","filename":""}
         * verify : false
         */

        private String servers;
        private String minMetadataRefreshInterval;
        private String username;
        private String password;
        private String syncTimeout;
        private String maxBatchBytes;
        private String compression;
        private String sendBuffer;
        private boolean queryApiVersions;
        private boolean ssl;
        private CacertfileBean cacertfile;
        private KeyfileBean keyfile;
        private CertfileBean certfile;
        private boolean verify;
    }

    @Data
    public static class WebHookConfigBean {

        /**
         * url : http://localhost:8080
         * connect_timeout : 5s
         * request_timeout : 5s
         * pool_size : 8
         * enable_pipelining : true
         * cacertfile : {"file":"","filename":""}
         * keyfile : {"file":"","filename":""}
         * certfile : {"file":"","filename":""}
         * verify : false
         */

        private String url;
        private String connectTimeout;
        private String requestTimeout;
        private Integer poolSize;
        private boolean enablePipelining;
        private CacertfileBean cacertfile;
        private KeyfileBean keyfile;
        private CertfileBean certfile;
        private boolean verify;
    }

    @Data
    public static class CacertfileBean {
        /**
         * file :
         * filename :
         */

        private String file;
        private String filename;
    }

    @Data
    public static class KeyfileBean {
        /**
         * file :
         * filename :
         */

        private String file;
        private String filename;
    }

    @Data
    public static class CertfileBean {
        /**
         * file :
         * filename :
         */

        private String file;
        private String filename;
    }
}
