/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import org.hibernate.validator.constraints.Length;

import com.yuga.common.persistence.DataEntity;
import com.yuga.modules.sys.entity.User;

/**
 * 学生信息Entity
 * @author 曾康
 * @version 2016-10-23
 */
public class YgStudentUser extends DataEntity<YgStudentUser> {
	
	private static final long serialVersionUID = 1L;
	private String schoolNo;		// 学号
	private String contactUserName;		// 监护人姓名
	private String contactTelNo;		// 监护人电话
	private String address;		// 家庭地址
	private String gradeNo;		// 所属年级
	private User user;		// 所属用户ID
	
	public YgStudentUser() {
		super();
	}

	public YgStudentUser(String id){
		super(id);
	}

	@Length(min=0, max=10, message="学号长度必须介于 0 和 10 之间")
	public String getSchoolNo() {
		return schoolNo;
	}

	public void setSchoolNo(String schoolNo) {
		this.schoolNo = schoolNo;
	}
	
	@Length(min=0, max=10, message="监护人姓名长度必须介于 0 和 10 之间")
	public String getContactUserName() {
		return contactUserName;
	}

	public void setContactUserName(String contactUserName) {
		this.contactUserName = contactUserName;
	}
	
	@Length(min=0, max=15, message="监护人电话长度必须介于 0 和 15 之间")
	public String getContactTelNo() {
		return contactTelNo;
	}

	public void setContactTelNo(String contactTelNo) {
		this.contactTelNo = contactTelNo;
	}
	
	@Length(min=0, max=255, message="家庭地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=11, message="所属年级长度必须介于 0 和 11 之间")
	public String getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(String gradeNo) {
		this.gradeNo = gradeNo;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}