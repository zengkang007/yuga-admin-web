/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;

/**
 * 课程Entity
 * @author 曾康
 * @version 2016-10-23
 */
public class YgCourse extends DataEntity<YgCourse> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 学科名称
	
	public YgCourse() {
		super();
	}

	public YgCourse(String id){
		super(id);
	}

	@Length(min=0, max=10, message="学科名称长度必须介于 0 和 10 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}