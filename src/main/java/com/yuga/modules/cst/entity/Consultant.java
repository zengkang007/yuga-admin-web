/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.entity;

import com.yuga.modules.cst.registries.BookConst;
import com.yuga.modules.cst.registries.FormStatusConst;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.yuga.common.persistence.DataEntity;

/**
 * ConsultantEntity
 * @author zengk
 * @version 2017-01-06
 */
public class Consultant extends DataEntity<Consultant> {
	
	private static final long serialVersionUID = 1L;
	private String skillLevel;		// Skill Level
	private String name; //Consultant Name
	private String locationCity;		// Location City
	private String projectLength;		// Project Length
	private Date startDate;		// Start Date
	private String outSource;		// Out Source
	private String deliveryMode;		// Delivery Mode
	private String govtClearnce;		// Govt Clearnce
	private String notes;		// Notes
	private Integer formStatus = FormStatusConst.AUDITING;//form status 0:un approve 1: approved -1:deny
	private Integer isBook = BookConst.UN_BOOK;//default status
	private Date createDate;
	private String submitter;
	private String bookUserId;
	private String biddingLast;
	private String totalBidding;
	private String currentBid;

	public Consultant() {
		super();
	}

	public Consultant(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(String currentBid) {
		this.currentBid = currentBid;
	}

	public String getBiddingLast() {
		return biddingLast;
	}

	public void setBiddingLast(String biddingLast) {
		this.biddingLast = biddingLast;
	}

	public String getTotalBidding() {
		return totalBidding;
	}

	public void setTotalBidding(String totalBidding) {
		this.totalBidding = totalBidding;
	}

	public String getBookUserId() {
		return bookUserId;
	}

	public void setBookUserId(String bookUserId) {
		this.bookUserId = bookUserId;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Length(min=0, max=11, message="Skill Level长度必须介于 0 和 11 之间")
	public String getSkillLevel() {
		return skillLevel;
	}

	public Integer getIsBook() {
		return isBook;
	}

	public void setIsBook(Integer isBook) {
		this.isBook = isBook;
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

	public Integer getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(Integer formStatus) {
		this.formStatus = formStatus;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
}