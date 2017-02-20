/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.checker;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.yuga.common.persistence.DataEntity;

/**
 * 巡检时间主表Entity
 * 
 * @author 曾康
 * @version 2016-10-26
 */
public class YgTimeCheck extends DataEntity<YgTimeCheck> {

	private static final long serialVersionUID = 1L;
	private String name; // 名称
	private String startTime; // 开始时间
	private String endTime; // 结束时间
	private List<YgTimeField> ygTimeFieldList = Lists.newArrayList();// 巡查时间子项

	public YgTimeCheck() {
		super();
	}

	public YgTimeCheck(String id) {
		super(id);
	}

	public List<YgTimeField> getYgTimeFieldList() {
		return ygTimeFieldList;
	}

	public void setYgTimeFieldList(List<YgTimeField> ygTimeFieldList) {
		this.ygTimeFieldList = ygTimeFieldList;
	}

	@Length(min = 0, max = 50, message = "名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}