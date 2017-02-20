/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;
import com.yuga.modules.sys.entity.User;

/**
 * 教师信息Entity
 * @author 曾康
 * @version 2016-10-23
 */
public class YgTeacherUser extends DataEntity<YgTeacherUser> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 所属用户ID
	private String classId;		// 所属班级
	
	public YgTeacherUser() {
		super();
	}

	public YgTeacherUser(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=11, message="所属班级长度必须介于 0 和 11 之间")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
}