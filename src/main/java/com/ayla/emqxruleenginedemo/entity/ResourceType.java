package com.ayla.emqxruleenginedemo.entity;

import java.util.Date;
import lombok.Data;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

/**
 * 
 * @author Gary 2021年9月3日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "resource_types")
public class ResourceType implements Serializable {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 资源类型名称
	 */
	private String name;

	/**
	 * 资源类型标题
	 */
	private String title;

	/**
	 * 资源类型提供方
	 */
	private String provider;

	/**
	 * 资源类型描述
	 */
	private String description;

	/**
	 * 资源类型 json
	 */
	private String rawJson;

	/**
	 * 创建时间
	 */
	@CreationTimestamp
	private Date createAt;

	/**
	 * 更新时间
	 */
	@UpdateTimestamp
	private Date updateAt;

}
