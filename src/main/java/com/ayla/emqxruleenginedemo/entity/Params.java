package com.ayla.emqxruleenginedemo.entity;

import lombok.Data;

public @Data class Params{
	private String maxTotalBytes;
	private String segmentsBytes;
	private String partitionCountRefreshInterval;
	private String requiredAcks;
	private String cacheMode;
	private String topic;
	private String resource;
	private String type;
	private String strategy;
	private String payloadTmpl;
	private String key;
}