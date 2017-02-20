/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;

/**
 * 节次时间Entity
 * @author 曾康
 * @version 2016-11-01
 */
public class CourseSectionTime extends DataEntity<CourseSectionTime> {
	
	private static final long serialVersionUID = 1L;
	private String sectionId;		// 节次
	private String startTime;		// 开始时间
	private String endTime;		// 结束时间
	
	public CourseSectionTime() {
		super();
	}

	public CourseSectionTime(String id){
		super(id);
	}

	@Length(min=0, max=11, message="节次长度必须介于 0 和 11 之间")
	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	
	@Length(min=0, max=10, message="开始时间长度必须介于 0 和 10 之间")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Length(min=0, max=10, message="结束时间长度必须介于 0 和 10 之间")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}