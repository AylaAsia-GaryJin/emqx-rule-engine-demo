package com.ayla.emqxruleenginedemo.entity;

import lombok.Data;

public @Data class MetricsItem{
	private String node;
	private int speedMax;
	private double speedLast5m;
	private int matched;
	private double speed;
	private int success;
	private int taken;
	private int failed;
}