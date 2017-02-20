/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.checker;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;

/**
 * 巡检子表Entity
 * @author 曾康
 * @version 2016-10-26
 */
public class YgTimeField extends DataEntity<YgTimeField> {
	
	private static final long serialVersionUID = 1L;
	private String fieldName;		// 字段名称
	private String filedValue;		// 字段对应名称
	private String checkTimeId;		// 所属时间方案
	private String defaultMark;	//默认分数
	private String mark;		// 分数
	private YgTimeCheck ygTimeCheck;
	
	public YgTimeField() {
		super();
	}

	public YgTimeField(String id){
		super(id);
	}
	
	public String getDefaultMark() {
		return defaultMark;
	}

	public void setDefaultMark(String defaultMark) {
		this.defaultMark = defaultMark;
	}

	public YgTimeCheck getYgTimeCheck() {
		return ygTimeCheck;
	}

	public void setYgTimeCheck(YgTimeCheck ygTimeCheck) {
		this.ygTimeCheck = ygTimeCheck;
	}

	public YgTimeField(YgTimeCheck ygTimeCheck) {
		super();
		this.ygTimeCheck = ygTimeCheck;
	}

	@Length(min=0, max=50, message="字段名称长度必须介于 0 和 50 之间")
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Length(min=0, max=10, message="字段对应名称长度必须介于 0 和 10 之间")
	public String getFiledValue() {
		return filedValue;
	}

	public void setFiledValue(String filedValue) {
		this.filedValue = filedValue;
	}
	
	@Length(min=0, max=32, message="所属时间方案长度必须介于 0 和 32 之间")
	public String getCheckTimeId() {
		return checkTimeId;
	}

	public void setCheckTimeId(String checkTimeId) {
		this.checkTimeId = checkTimeId;
	}
	
	@Length(min=0, max=5, message="分数长度必须介于 0 和 5 之间")
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
}