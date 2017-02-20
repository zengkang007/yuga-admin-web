/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yuga.common.persistence.DataEntity;

/**
 * NotificationEntity
 * @author 曾康
 * @version 2017-02-16
 */
public class YgNotification extends DataEntity<YgNotification> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// Title
	private String content;		// Content
	private Date createTime;		// Send time
	private String priority;		// Priority
	private String isRead;		// Is read
	
	public YgNotification() {
		super();
	}

	public YgNotification(String id){
		super(id);
	}

	@Length(min=0, max=255, message="Title长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1024, message="Content长度必须介于 0 和 1024 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=2, message="Priority长度必须介于 0 和 2 之间")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Length(min=0, max=2, message="Is read长度必须介于 0 和 2 之间")
	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	
}