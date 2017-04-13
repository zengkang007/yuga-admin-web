/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.dao;

import com.yuga.common.persistence.CrudDao;
import com.yuga.common.persistence.annotation.MyBatisDao;
import com.yuga.modules.cst.entity.Bidding;
import com.yuga.modules.cst.entity.TradeBid;

import java.math.BigDecimal;
import java.util.List;

/**
 * BiddingDAO接口
 * @author zengk
 * @version 2017-03-02
 */
@MyBatisDao
public interface BiddingDao extends CrudDao<Bidding> {
	String getCurrentBid(String consultantId);
	List<Bidding> findBidderList(Bidding bidding);
	List<Bidding> findOtherBidderList(Bidding bidding);
	List<Bidding> findTradeBidList(Bidding bidding);
	List<TradeBid> findTodoBidderList(Bidding bidding);
}