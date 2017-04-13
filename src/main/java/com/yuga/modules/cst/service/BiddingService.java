/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.service;

import java.math.BigDecimal;
import java.util.List;

import com.yuga.common.config.Global;
import com.yuga.modules.cst.entity.Bidding;
import com.yuga.modules.cst.entity.Consultant;
import com.yuga.modules.cst.entity.TradeBid;
import com.yuga.modules.cst.registries.FormStatusConst;
import com.yuga.modules.oa.entity.OaNotify;
import com.yuga.modules.oa.service.OaNotifyService;
import com.yuga.modules.sys.entity.User;
import com.yuga.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.cst.dao.BiddingDao;

/**
 * BiddingService
 * @author zengk
 * @version 2017-03-02
 */
@Service
@Transactional(readOnly = true)
public class BiddingService extends CrudService<BiddingDao, Bidding> {

	@Autowired
	private BiddingDao biddingDao;

	@Autowired
	private OaNotifyService notifyService;

	@Autowired
	private ConsultantService consultantService;


	public Bidding get(String id) {
		return super.get(id);
	}
	
	public List<Bidding> findList(Bidding bidding) {
		return super.findList(bidding);
	}

	public List<Bidding> findBidderList(Bidding bidding){
		return  biddingDao.findBidderList(bidding);
	}

	public List<Bidding> findOtherBidderList(Bidding bidding){
		return biddingDao.findOtherBidderList(bidding);
	}

	public List<Bidding> findTradeBidList(Bidding bidding){
		return biddingDao.findTradeBidList(bidding);
	}

	public List<TradeBid> findTodoBidderList(Bidding bidding){
		return biddingDao.findTodoBidderList(bidding);
	}

	/**
	 * 更新bidder状态
	 * @param bidding
	 */
	@Transactional(readOnly = false)
	public void updateBidderStatus(Bidding bidding){
		//1.无论接受、拒绝均发出通知给bidder
		//2.如果接受，则管理员也可见，管理员也会收到通知
		List<User> listAdmin = UserUtils.getAdminUserList();
		String notifyTitle = null;
		String notifyContent = null;
		if(bidding.getFormStatus() == FormStatusConst.BID_ACCEPT) {
			logger.info("accept the bid request.");
			notifyTitle = Global.getConfig("notify.bidding.admin.title");
			notifyContent = Global.getConfig("notify.bidding.admin.content");
			for(User adminUser : listAdmin) {
				//通知管理员，准备联系双方
				notifyTitle = notifyTitle.replace("{BIDDER}", bidding.getSubmitterName());
				notifyTitle = notifyTitle.replace("{BID}", bidding.getId());
				notifyContent = notifyContent.replace("{BIDDER}", bidding.getSubmitterName());
				notifyContent = notifyContent.replace("{BID}", bidding.getId());
				notifyService.createNotify(bidding.getConsultantId(),
						adminUser.getId(),
						notifyTitle,
						notifyContent,
						OaNotify.TYPE_NOTIFY
				);
				logger.info("notify admin title:" + notifyTitle + ", content:" + notifyContent);
			}

			logger.info("通知bidder,bid已经被接受");
			notifyTitle = Global.getConfig("notify.accept.title");
			notifyTitle = notifyTitle.replace("{CONSULTANT}", UserUtils.getUser().getName());
			notifyContent = Global.getConfig("notify.accept.content");
			notifyContent = notifyContent.replace("{CONSULTANT}", UserUtils.getUser().getName());
			notifyService.createNotify(bidding.getConsultantId(),
					bidding.getBidderId(),
					notifyTitle,
					notifyContent,
					OaNotify.TYPE_NOTIFY
			);

			logger.info("通知其他bidder，该bid已经被接受，不能够参与竞标了。");
			//查询竞标的其他用户
			List<Bidding> listOtherBidding = findOtherBidderList(bidding);
			for(Bidding otherBidding : listOtherBidding) {
				logger.info("通知其他用户该bid已经被ID:"+bidding.getId()+"给领取.");
				notifyTitle = Global.getConfig("notify.bidding.other.title");
				notifyTitle = notifyTitle.replace("{ID}", UserUtils.getUser().getName());
				notifyContent = Global.getConfig("notify.bidding.other.content");
				notifyContent = notifyContent.replace("{ID}", UserUtils.getUser().getName());
				notifyService.createNotify(bidding.getConsultantId(),
						otherBidding.getBidderId(),
						notifyTitle,
						notifyContent,
						OaNotify.TYPE_NOTIFY
				);
				logger.info("修改其他用户状态。");
				otherBidding.setFormStatus(FormStatusConst.BID_OUT);
				biddingDao.update(otherBidding);
			}
			//设置consultant状态
			String consultantId = bidding.getConsultantId();
			Consultant consultant = consultantService.get(consultantId);
			if (consultant != null) {
				logger.info("修改顾问状态。");
				consultant.setFormStatus(FormStatusConst.CONSULTANT_BIDDED);
				consultantService.save(consultant);
				logger.info("顾问:" + consultant.getName() + "已经被预定。");
			}
		} else {
			logger.error("通知bidder,bid已经被拒绝");
			notifyTitle = Global.getConfig("notify.reject.title");
			notifyContent = Global.getConfig("notify.reject.content");
			notifyTitle = notifyTitle.replace("{CONSULTANT}", UserUtils.getUser().getName());
			notifyContent = notifyContent.replace("{CONSULTANT}", UserUtils.getUser().getName());
			notifyService.createNotify(bidding.getConsultantId(),
					bidding.getBidderId(),
					notifyTitle,
					notifyContent,
					OaNotify.TYPE_NOTIFY
			);
		}
		save(bidding);
		logger.info("save bidding.");
	}

	public Page<Bidding> findPage(Page<Bidding> page, Bidding bidding) {
		return super.findPage(page, bidding);
	}
	
	@Transactional(readOnly = false)
	public void save(Bidding bidding) {
		super.save(bidding);
	}
	
	@Transactional(readOnly = false)
	public void delete(Bidding bidding) {
		super.delete(bidding);
	}

	/**
	 * 获取最后一次报价
	 * @param consultantId
	 * @return
	 */
	public BigDecimal getCurrentBid(String consultantId){
		String currentBid = biddingDao.getCurrentBid(consultantId);
		if(currentBid == null) { currentBid = "0";}
		return new BigDecimal(currentBid);
	}
}