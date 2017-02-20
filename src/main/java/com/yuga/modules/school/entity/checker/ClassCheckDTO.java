package com.yuga.modules.school.entity.checker;

import com.yuga.common.persistence.DataEntity;

public class ClassCheckDTO  extends DataEntity<ClassCheckDTO> {
	private static final long serialVersionUID = 1L;
	private String className;
	private String weekName;
	private String sectionName;
	private String courseName;
	private String teacherName;
	private String totalMark;
	private String classAvatar;
	public String getClassName() {
		return className;
	}
	public String getClassAvatar() {
		return classAvatar;
	}
	public void setClassAvatar(String classAvatar) {
		this.classAvatar = classAvatar;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getWeekName() {
		return weekName;
	}
	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTotalMark() {
		return totalMark;
	}
	public void setTotalMark(String totalMark) {
		this.totalMark = totalMark;
	}
	
	
}
