/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;

/**
 * 课时维护Entity
 * @author 曾康
 * @version 2016-10-23
 */
public class YgCourseTime extends DataEntity<YgCourseTime> {
	
	private static final long serialVersionUID = 1L;
	private String courseName;		// 课程章节
	private String timeRange;		// 上课时间
	private String param1;		// 保留字段1
	private String param2;		// 保留字段2
	private String param3;		// 保留字段3
	private String param4;		// 保留字段4
	
	public YgCourseTime() {
		super();
	}

	public YgCourseTime(String id){
		super(id);
	}

	@Length(min=0, max=50, message="课程章节长度必须介于 0 和 50 之间")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	@Length(min=0, max=120, message="上课时间长度必须介于 0 和 120 之间")
	public String getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
	
	@Length(min=0, max=120, message="保留字段1长度必须介于 0 和 120 之间")
	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}
	
	@Length(min=0, max=120, message="保留字段2长度必须介于 0 和 120 之间")
	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}
	
	@Length(min=0, max=120, message="保留字段3长度必须介于 0 和 120 之间")
	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}
	
	@Length(min=0, max=120, message="保留字段4长度必须介于 0 和 120 之间")
	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}
	
}