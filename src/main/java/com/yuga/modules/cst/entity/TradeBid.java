package com.yuga.modules.cst.entity;

import java.util.Date;

/**
 * Created by zengk on 2017/3/19.
 */
public class TradeBid {
    private String publisherName;
    private String publisherEmail;
    private String publisherMobile;
    private String bidderName;
    private String bidderEmail;
    private String bidderMobile;
    private Integer formStatus;
    private String currentBid;
    private Date biddingTime;

    public Date getBiddingTime() {
        return biddingTime;
    }

    public void setBiddingTime(Date biddingTime) {
        this.biddingTime = biddingTime;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherEmail() {
        return publisherEmail;
    }

    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }

    public String getPublisherMobile() {
        return publisherMobile;
    }

    public void setPublisherMobile(String publisherMobile) {
        this.publisherMobile = publisherMobile;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public String getBidderEmail() {
        return bidderEmail;
    }

    public void setBidderEmail(String bidderEmail) {
        this.bidderEmail = bidderEmail;
    }

    public String getBidderMobile() {
        return bidderMobile;
    }

    public void setBidderMobile(String bidderMobile) {
        this.bidderMobile = bidderMobile;
    }

    public Integer getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(Integer formStatus) {
        this.formStatus = formStatus;
    }

    public String getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(String currentBid) {
        this.currentBid = currentBid;
    }
}
