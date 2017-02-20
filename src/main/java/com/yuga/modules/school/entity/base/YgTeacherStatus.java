/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;

/**
 * 教师教学情况Entity
 * @author 曾康
 * @version 2016-10-23
 */
public class YgTeacherStatus extends DataEntity<YgTeacherStatus> {
	
	private static final long serialVersionUID = 1L;
	private String level;		// 对应等级
	private String content;		// 对应说明
	
	public YgTeacherStatus() {
		super();
	}

	public YgTeacherStatus(String id){
		super(id);
	}

	@Length(min=0, max=10, message="对应等级长度必须介于 0 和 10 之间")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@Length(min=0, max=255, message="对应说明长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}