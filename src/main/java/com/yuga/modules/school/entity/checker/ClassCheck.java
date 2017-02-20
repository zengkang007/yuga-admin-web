/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.checker;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;

/**
 * 巡查信息Entity
 * @author 曾康
 * @version 2016-10-17
 */
public class ClassCheck extends DataEntity<ClassCheck> {
	
	private static final long serialVersionUID = 1L;
	private String courseSection;		// 课程章节
	private String teacherId;		// 教师ID
	private String checkerId;		// 巡查人
	private String condition1;		// 教师教学好习惯落实
	private String condition2;		// 学生学习好习惯落实
	private String condition3;		// 使用多媒体情况
	
	public ClassCheck() {
		super();
	}

	public ClassCheck(String id){
		super(id);
	}

	@Length(min=0, max=2, message="课程章节长度必须介于 0 和 2 之间")
	public String getCourseSection() {
		return courseSection;
	}

	public void setCourseSection(String courseSection) {
		this.courseSection = courseSection;
	}
	
	@Length(min=0, max=11, message="教师ID长度必须介于 0 和 11 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	@Length(min=0, max=11, message="巡查人长度必须介于 0 和 11 之间")
	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}
	
	@Length(min=0, max=255, message="教师教学好习惯落实长度必须介于 0 和 255 之间")
	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}
	
	@Length(min=0, max=255, message="学生学习好习惯落实长度必须介于 0 和 255 之间")
	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}
	
	@Length(min=0, max=255, message="使用多媒体情况长度必须介于 0 和 255 之间")
	public String getCondition3() {
		return condition3;
	}

	public void setCondition3(String condition3) {
		this.condition3 = condition3;
	}
	
}