/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.entity.base;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yuga.common.persistence.DataEntity;

/**
 * 多媒体使用情况Entity
 * @author 曾康
 * @version 2016-10-23
 */
public class YgMultimediaStatus extends DataEntity<YgMultimediaStatus> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 设备名称
	private String code;		// 产品编号
	private Date productDate;		// 生产时间
	private String status;		// 设备状态 0：正常 1：损坏 2：维修中
	private String owner;		// 使用者姓名
	private Date createTime;		// create_time
	
	public YgMultimediaStatus() {
		super();
	}

	public YgMultimediaStatus(String id){
		super(id);
	}

	@Length(min=1, max=30, message="设备名称长度必须介于 1 和 30 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="产品编号长度必须介于 0 和 50 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	
	@Length(min=0, max=1, message="设备状态 0：正常 1：损坏 2：维修中长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=5, message="使用者姓名长度必须介于 0 和 5 之间")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}