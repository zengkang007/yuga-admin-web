/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.checker;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.yuga.common.persistence.DataEntity;
import com.yuga.modules.sys.entity.User;

/**
 * 巡查情况Entity
 * 
 * @author 曾康
 * @version 2016-11-02
 */
public class YgClassCheck extends DataEntity<YgClassCheck> {

	private static final long serialVersionUID = 1L;
	private String classId; // 班级
	private String weekId; // 周
	private String sectionId; // 章节
	private String courseId; // 课程
	private String teacherId; // 教师
	private String checkItems;//巡检项目
	private Integer totalMark;//总分统计
	private Integer checkTimeId;//巡查方案id
	private String condition1; // 保留字段1
	private String condition2; // 保留字段2
	private String condition3; // 保留字段3
	private Integer totalAbsence;//缺勤总分
	private Integer amoutAbsence;//缺勤人数
	private Date checkTime;
	private User user; // 教师信息
	private YgTimeCheck timeCheck = new YgTimeCheck();
	
    public YgClassCheck(String classId,String weekId,String sectionId,String courseId,String teacherId,String checkItems)  
    {  

    	//this.checkTime=checkTime;
        this.classId=classId; // 班级
        this.weekId=weekId; // 周
        this.sectionId=sectionId; // 章节
        this.courseId=courseId; // 课程
        this.teacherId=teacherId; // 教师
        this.checkItems=checkItems;//巡检项目
    	//this.amoutAbsence=amoutAbsence;//缺勤人数
        //this.totalMark = totalMark+totalAbsence;//总分统计
    	//this.user=user; // 教师信息
    } 

	public YgClassCheck() {
		super();
	}

	public YgClassCheck(String id) {
		super(id);
	}

	public Integer getAmoutAbsence() {
		return amoutAbsence;
	}

	public void setAmoutAbsence(Integer amoutAbsence) {
		this.amoutAbsence = amoutAbsence;
	}

	public Integer getTotalAbsence() {
		return totalAbsence;
	}

	public void setTotalAbsence(Integer totalAbsence) {
		this.totalAbsence = totalAbsence;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getCheckTimeId() {
		return checkTimeId;
	}

	public void setCheckTimeId(Integer checkTimeId) {
		this.checkTimeId = checkTimeId;
	}

	public YgTimeCheck getTimeCheck() {
		return timeCheck;
	}

	public Integer getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(Integer totalMark) {
		this.totalMark = totalMark;
	}

	public void setTimeCheck(YgTimeCheck timeCheck) {
		this.timeCheck = timeCheck;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min = 1, max = 11, message = "班级长度必须介于 1 和 11 之间")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Length(min = 1, max = 2, message = "周长度必须介于 1 和 2 之间")
	public String getWeekId() {
		return weekId;
	}

	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}

	@Length(min = 1, max = 11, message = "章节长度必须介于 1 和 11 之间")
	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	@Length(min = 1, max = 11, message = "课程长度必须介于 1 和 11 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Length(min = 1, max = 50, message = "教师长度必须介于 1 和 50 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@Length(min = 0, max = 255, message = "保留字段1长度必须介于 0 和 255 之间")
	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}

	@Length(min = 0, max = 255, message = "保留字段2长度必须介于 0 和 255 之间")
	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}

	@Length(min = 0, max = 255, message = "保留字段3长度必须介于 0 和 255 之间")
	public String getCondition3() {
		return condition3;
	}

	public void setCondition3(String condition3) {
		this.condition3 = condition3;
	}

	public String getCheckItems() {
		return checkItems;
	}

	public void setCheckItems(String checkItems) {
		this.checkItems = checkItems;
	}

	public List<String> getCheckItemsList() {
		List<String> list = Lists.newArrayList();
		if (checkItems != null) {
			for (String s : StringUtils.split(checkItems, ",")) {
				list.add(s);
			}
		}
		return list;
	}

	public void setCheckItemsList(List<String> list) {
		checkItems = "," + StringUtils.join(list, ",") + ",";
	}
}