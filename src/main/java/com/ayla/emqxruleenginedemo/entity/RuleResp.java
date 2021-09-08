package com.ayla.emqxruleenginedemo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public @Data class RuleResp{
	private String rawsql;
	private String onActionFailed;
	@JsonProperty("for")
	private List<String> jsonMemberFor;
	private String description;
	private List<MetricsItem> metrics;
	private String id;
	private List<ActionsItem> actions;
	private boolean enabled;
}