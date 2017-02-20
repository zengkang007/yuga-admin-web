/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;

/**
 * 教师信息Entity
 * @author 曾康
 * @version 2016-10-17
 */
public class YgClass extends DataEntity<YgClass> {
	
	private static final long serialVersionUID = 1L;
	private Date createTime;		// 创建时间
	private String className;		// 班级名称
	private String classGrade;		// 年级
	private String classAddress;		// 班级地址
	private String classCount;		// 班级人数
	private String classAvatar;		//班级图标
	public YgClass() {
		super();
	}

	public YgClass(String id){
		super(id);
	}

	public String getClassAvatar() {
		return classAvatar;
	}

	public void setClassAvatar(String classAvatar) {
		this.classAvatar = classAvatar;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=255, message="班级名称长度必须介于 0 和 255 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Length(min=0, max=1, message="年级长度必须介于 0 和 1 之间")
	public String getClassGrade() {
		return classGrade;
	}

	public void setClassGrade(String classGrade) {
		this.classGrade = classGrade;
	}

	public String getClassAddress() {
		return classAddress;
	}

	public void setClassAddress(String classAddress) {
		this.classAddress = classAddress;
	}

	public String getClassCount() {
		return classCount;
	}

	public void setClassCount(String classCount) {
		this.classCount = classCount;
	}
	
}