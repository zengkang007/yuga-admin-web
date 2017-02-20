/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.entity;

import org.hibernate.validator.constraints.Length;
import com.yuga.modules.sys.entity.User;

import com.yuga.common.persistence.DataEntity;

/**
 * consultant bookingEntity
 * @author 曾康
 * @version 2017-02-16
 */
public class YgConsultantBook extends DataEntity<YgConsultantBook> {
	
	private static final long serialVersionUID = 1L;
	private String consultantId;		// Consultant id
	private User user;		// user_id
	
	public YgConsultantBook() {
		super();
	}

	public YgConsultantBook(String id){
		super(id);
	}

	@Length(min=0, max=32, message="Consultant id长度必须介于 0 和 32 之间")
	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}