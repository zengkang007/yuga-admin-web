/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.entity;

import org.hibernate.validator.constraints.Length;
import com.yuga.modules.sys.entity.User;

import com.yuga.common.persistence.DataEntity;

/**
 * FavoritesEntity
 * @author zengk
 * @version 2017-04-13
 */
public class YgFavorites extends DataEntity<YgFavorites> {
	
	private static final long serialVersionUID = 1L;
	private String jobId;		// Job ID
	private String consultantId;		// Consultant ID
	private String createTime;		// Create Time
	private User user;		// Belond user.
	
	public YgFavorites() {
		super();
	}

	public YgFavorites(String id){
		super(id);
	}

	@Length(min=0, max=45, message="Job ID长度必须介于 0 和 45 之间")
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	@Length(min=0, max=45, message="Consultant ID长度必须介于 0 和 45 之间")
	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}
	
	@Length(min=0, max=45, message="Create Time长度必须介于 0 和 45 之间")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}