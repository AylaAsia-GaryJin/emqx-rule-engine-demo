package com.ayla.emqxruleenginedemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private ParamsBean params;
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
    public static class ParamsBean {
        private VerifyBean verify;
        private UsernameBean username;
        private SslBean ssl;
        @JsonProperty("pool_size")
        private PoolSizeBean poolSize;
        private PasswordBean password;
        private NodesBean nodes;
        private KeyspaceBean keyspace;
        private KeyfileBean keyfile;
        private CertfileBean certfile;
        private CafileBean cafile;
        @JsonProperty("auto_reconnect")
        private AutoReconnectBean autoReconnect;
    }

    @NoArgsConstructor
    @Data
    public static class VerifyBean {
        private String type;
        private TitleBean title;
        private int order;
        private DescriptionBean description;
        @JsonProperty("default")
        private boolean defaultX;
    }

    @NoArgsConstructor
    @Data
    public static class DescriptionBean {
        private String zh;
        private String en;
    }

    @NoArgsConstructor
    @Data
    public static class UsernameBean {
        private String type;
        private TitleBean title;
        private int order;
        private DescriptionBean description;
        @JsonProperty("default")
        private String defaultX;
    }

    @NoArgsConstructor
    @Data
    public static class SslBean {
        private String type;
        private TitleBean title;
        private int order;
        private DescriptionBean description;
        @JsonProperty("default")
        private boolean defaultX;
    }

    @NoArgsConstructor
    @Data
    public static class PoolSizeBean {
        private String type;
        private TitleBean title;
        private int order;
        private DescriptionBean description;
        @JsonProperty("default")
        private int defaultX;
    }

    @NoArgsConstructor
    @Data
    public static class PasswordBean {
        private String type;
        private TitleBean title;
        private int order;
        private DescriptionBean description;
        @JsonProperty("default")
        private String defaultX;
    }

    @NoArgsConstructor
    @Data
    public static class NodesBean {
        private String type;
        private TitleBean title;
        private boolean required;
        private int order;
        private DescriptionBean description;
        @JsonProperty("default")
        private String defaultX;
    }

    @NoArgsConstructor
    @Data
    public static class KeyspaceBean {
        private String type;
        private TitleBean title;
        private boolean required;
        private int order;
        private DescriptionBean description;
    }

    @NoArgsConstructor
    @Data
    public static class KeyfileBean {
        private String type;
        private TitleBean title;
        private int order;
        private DescriptionBean description;
        @JsonProperty("default")
        private String defaultX;
    }

    @NoArgsConstructor
    @Data
    public static class CertfileBean {
        private String type;
        private TitleBean title;
        private int order;
        private DescriptionBean description;
        @JsonProperty("default")
        private String defaultX;
    }

    @NoArgsConstructor
    @Data
    public static class CafileBean {
        private String type;
        private TitleBean title;
        private int order;
        private DescriptionBean description;
        @JsonProperty("default")
        private String defaultX;
    }

    @NoArgsConstructor
    @Data
    public static class AutoReconnectBean {
        private String type;
        private TitleBean title;
        private Integer order;
        private DescriptionBean description;
        @JsonProperty("default")
        private String defaultX;

        @NoArgsConstructor
        @Data
        public static class TitleBean {
            private String zh;
            private String en;
        }
    }
}
