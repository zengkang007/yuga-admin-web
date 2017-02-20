/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;
import com.yuga.modules.sys.entity.User;

/**
 * 课表基础信息Entity
 * @author 曾康
 * @version 2016-10-26
 */
public class BaseCourse extends DataEntity<BaseCourse> {
	
	private static final long serialVersionUID = 1L;
	private String classId;			//班级Id
	private String className;		// 班级名称
	private Integer weekId;		// 星期
	private Integer section;		// 节次
	private Integer courseId;		// 课程
	private String teacherId;		// 教师
	private String monthStart;
	private String monthEnd;
	private String qType;//查询类型 1:通过巡查方式查询
	private User user; //教师信息
	public BaseCourse() {
		super();
	}

	public BaseCourse(String id){
		super(id);
	}

	public String getMonthStart() {
		return monthStart;
	}

	public String getqType() {
		return qType;
	}

	public void setqType(String qType) {
		this.qType = qType;
	}

	public void setMonthStart(String monthStart) {
		this.monthStart = monthStart;
	}

	public String getMonthEnd() {
		return monthEnd;
	}

	public void setMonthEnd(String monthEnd) {
		this.monthEnd = monthEnd;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min=0, max=20, message="班级名称长度必须介于 0 和 20 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}
	
	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}
	
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
}