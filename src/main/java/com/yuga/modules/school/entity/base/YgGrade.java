/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;

/**
 * 年级级别Entity
 * @author 曾康
 * @version 2016-10-23
 */
public class YgGrade extends DataEntity<YgGrade> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 年级名称
	
	public YgGrade() {
		super();
	}

	public YgGrade(String id){
		super(id);
	}

	@Length(min=0, max=50, message="年级名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}