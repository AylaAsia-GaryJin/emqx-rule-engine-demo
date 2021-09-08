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
@Table(name = "message_records")
public class MessageRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 消息ID
	 */
	private String messageId;

	/**
	 * 消息体
	 */
	private String payload;

	/**
	 * 消息来源 topic
	 */
	private String topic;

	/**
	 * 消息类型
	 */
	private String messageType;

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
