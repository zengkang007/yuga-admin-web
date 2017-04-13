/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.yuga.common.persistence.DataEntity;

/**
 * BiddingEntity
 * @author zengk
 * @version 2017-03-02
 */
public class Bidding extends DataEntity<Bidding> {
	private static final long serialVersionUID = 1L;
	private String bidderId;		// Bidder
	private BigDecimal currentBid;		// Current Bid
	private BigDecimal maxBid;		// Max Bid
	private BigDecimal floorPrice;	// Floor Price
	private String consultantId;		// Consultant Id
	private Date biddingTime;		// Bidding Time
	private Integer formStatus;
	private String submitterName;

	public Integer getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(Integer formStatus) {
		this.formStatus = formStatus;
	}

	public String getSubmitterName() {
		return submitterName;
	}

	public void setSubmitterName(String submitterName) {
		this.submitterName = submitterName;
	}

	public Bidding() {
		super();
	}

	public Bidding(String id){
		super(id);
	}

	@Length(min=1, max=32, message="Bidder长度必须介于 1 和 32 之间")
	public String getBidderId() {
		return bidderId;
	}

	public BigDecimal getMaxBid() {
		return maxBid;
	}

	public BigDecimal getFloorPrice() {
		return floorPrice;
	}

	public void setFloorPrice(BigDecimal floorPrice) {
		this.floorPrice = floorPrice;
	}

	public void setMaxBid(BigDecimal maxBid) {
		this.maxBid = maxBid;
	}

	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}
	
	public BigDecimal getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(BigDecimal currentBid) {
		this.currentBid = currentBid;
	}
	
	@Length(min=1, max=32, message="Consultant Id长度必须介于 1 和 32 之间")
	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="Bidding Time不能为空")
	public Date getBiddingTime() {
		return biddingTime;
	}

	public void setBiddingTime(Date biddingTime) {
		this.biddingTime = biddingTime;
	}
	
}