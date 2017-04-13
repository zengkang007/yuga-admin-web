/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yuga.common.persistence.DataEntity;

/**
 * JobEntity
 * @author zengk
 * @version 2017-03-04
 */
public class YgJob extends DataEntity<YgJob> {
	
	private static final long serialVersionUID = 1L;
	private String skillLevel;		// Skill Leve
	private String locationCity;		// Location City
	private String projectLength;		// Project Length
	private Date startDate;		// Start Date
	private String outSource;		// Out Source
	private String deliveryMode;		// Delivery Mode
	private String govtClearnce;		// Govt Clearnce
	private String notes;		// Notes
	private String formStatus;		// 订单状态  0：待审核 1:审核通过 -1:审核未通过
	private String submitter;		// 提交者
	private String isBook;		// Booked
	private String bookUserId;		// 预订者ID
	
	public YgJob() {
		super();
	}

	public YgJob(String id){
		super(id);
	}

	@Length(min=1, max=11, message="Skill Leve长度必须介于 1 和 11 之间")
	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	
	@Length(min=0, max=11, message="Location City长度必须介于 0 和 11 之间")
	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}
	
	@Length(min=0, max=11, message="Project Length长度必须介于 0 和 11 之间")
	public String getProjectLength() {
		return projectLength;
	}

	public void setProjectLength(String projectLength) {
		this.projectLength = projectLength;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Length(min=0, max=2, message="Out Source长度必须介于 0 和 2 之间")
	public String getOutSource() {
		return outSource;
	}

	public void setOutSource(String outSource) {
		this.outSource = outSource;
	}
	
	@Length(min=0, max=2, message="Delivery Mode长度必须介于 0 和 2 之间")
	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	
	@Length(min=0, max=2, message="Govt Clearnce长度必须介于 0 和 2 之间")
	public String getGovtClearnce() {
		return govtClearnce;
	}

	public void setGovtClearnce(String govtClearnce) {
		this.govtClearnce = govtClearnce;
	}
	
	@Length(min=0, max=1000, message="Notes长度必须介于 0 和 1000 之间")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Length(min=0, max=2, message="订单状态  0：待审核 1:审核通过 -1:审核未通过长度必须介于 0 和 2 之间")
	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}
	
	@Length(min=0, max=32, message="提交者长度必须介于 0 和 32 之间")
	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	
	@Length(min=0, max=1, message="Booked长度必须介于 0 和 1 之间")
	public String getIsBook() {
		return isBook;
	}

	public void setIsBook(String isBook) {
		this.isBook = isBook;
	}
	
	@Length(min=0, max=32, message="预订者ID长度必须介于 0 和 32 之间")
	public String getBookUserId() {
		return bookUserId;
	}

	public void setBookUserId(String bookUserId) {
		this.bookUserId = bookUserId;
	}
	
}