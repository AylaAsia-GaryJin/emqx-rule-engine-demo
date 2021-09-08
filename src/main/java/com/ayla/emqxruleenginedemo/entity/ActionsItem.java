package com.ayla.emqxruleenginedemo.entity;

import java.util.List;
import lombok.Data;

public @Data class ActionsItem{
	private List<Object> fallbacks;
	private String name;
	private List<MetricsItem> metrics;
	private String id;
	private Params params;
}