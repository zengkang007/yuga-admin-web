/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuga.modules.cst.entity.Bidding;
import com.yuga.modules.cst.entity.Consultant;
import com.yuga.modules.cst.entity.TradeBid;
import com.yuga.modules.cst.service.ConsultantService;
import com.yuga.modules.oa.entity.OaNotify;
import com.yuga.modules.oa.service.OaNotifyService;
import com.yuga.modules.sys.entity.User;
import com.yuga.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuga.common.config.Global;
import com.yuga.common.persistence.Page;
import com.yuga.common.web.BaseController;
import com.yuga.common.utils.StringUtils;
import com.yuga.modules.cst.service.BiddingService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * BiddingController
 * @author zengk
 * @version 2017-03-02
 */
@Controller
@RequestMapping(value = "${adminPath}/cst/bidding")
public class BiddingController extends BaseController {

	@Autowired
	private BiddingService biddingService;

	@Autowired
	private OaNotifyService notifyService;

	@Autowired
	private ConsultantService consultantService;

	@ModelAttribute
	public Bidding get(@RequestParam(required=false) String id) {
		Bidding entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = biddingService.get(id);
		}
		if (entity == null){
			entity = new Bidding();
		}
		return entity;
	}

	/**
	 * 查询个人提交的bid
	 * @param bidding
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cst:bidding:view")
	@RequestMapping(value = {"list", ""})
	public String list(Bidding bidding, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Bidding> aPage = new Page<>(request, response);
		aPage.setOrderBy("id desc");
		bidding.setBidderId(UserUtils.getUser().getId());
		Page<Bidding> page = biddingService.findPage(aPage, bidding);
		model.addAttribute("page", page);
		return "modules/cst/biddingList";
	}

	@RequiresPermissions("cst:bidding:view")
	@RequestMapping(value = {"listTradeBid"})
	public String listTradeBid(Bidding bidding, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TradeBid> aPage = new Page<>(request, response);
		aPage.setOrderBy("id desc");
		List<TradeBid> listOut = biddingService.findTodoBidderList(bidding);
		aPage.setList(listOut);
		model.addAttribute("page", aPage);
		return "modules/cst/biddingTradeList";
	}

	@RequiresPermissions("cst:bidding:view")
	@RequestMapping(value = {"listBidder"})
	public String listBidder(Bidding bidding, HttpServletRequest request, HttpServletResponse response, Model model) {
		//1.设置查询范围区间
		Page<Bidding> page = new Page<>(request, response);
		page.setOrderBy("consultant_id desc");
		bidding.setBidderId(UserUtils.getUser().getId());
		bidding.setPage(page);
		List<Bidding> listOut = biddingService.findBidderList(bidding);
		page.setList(listOut);
		model.addAttribute("page", page);
		return "modules/cst/biddingConfirmList";
	}

	@RequestMapping(value = "bidConfirm")
	public String bidConfirm(Bidding bidding, RedirectAttributes redirectAttributes,
							 HttpServletRequest request, Model model) {
		String consultantId = request.getParameter("consultantId");
		BigDecimal currentBid = biddingService.getCurrentBid(consultantId);
		if(consultantId != null) {
			logger.info("Current bid is :" + currentBid);
			Consultant consultant = consultantService.get(consultantId);
			bidding.setFloorPrice(consultant.getBaseGrade());
		} else {
			logger.error("There is no default consultant id.");
		}
		bidding.setCurrentBid(currentBid);
		model.addAttribute("bidding", bidding);
		return "modules/cst/bidding/bidConfirm";
	}

	@RequiresPermissions("cst:bidding:view")
	@RequestMapping(value = "form")
	public String form(Bidding bidding, Model model) {
		model.addAttribute("bidding", bidding);
		return "modules/cst/BiddingForm";
	}

	@RequiresPermissions("cst:bidding:edit")
	@RequestMapping(value = "updateBidderStatus")
	public String updateBidderStatus(Bidding bidding, Model model, RedirectAttributes redirectAttributes) {
		biddingService.updateBidderStatus(bidding);
		model.addAttribute("bidding", new Bidding());
		addMessage(redirectAttributes, "update bidder status success.");
		return "redirect:"+Global.getAdminPath()+"/cst/bidding/listBidder/?repage";
	}

//	@RequiresPermissions("cst:bidding:edit")
	@RequestMapping(value = "save")
	public String save(Bidding bidding, Model model, RedirectAttributes redirectAttributes) {
		bidding.setBidderId(UserUtils.getUser().getId());
		bidding.setBiddingTime(new Date());
		bidding.setCurrentBid(bidding.getMaxBid());
		biddingService.save(bidding);
		String consultantId = bidding.getConsultantId();
		if(consultantId != null) {
			//发送通知

			if(bidding.getBidderId() == null) {
				logger.error("Bidder is empty.");
				addMessage(redirectAttributes, "bidder is empty .");
			}

			String title = Global.getConfig("notify.bidding.title");
			title = title.replace("{BIDDER}", UserUtils.getUser().getName());

			String content = Global.getConfig("notify.bidding.content");
			content = content.replace("{BID}", bidding.getMaxBid().toPlainString());
			content = content.replace("{BIDDER}", UserUtils.getUser().getName());

			//发送通知
			notifyService.createNotify(consultantId, bidding.getBidderId(), title, content, OaNotify.TYPE_BID);
			logger.info("consultantId : " + consultantId
					+ " bidding.getBidderId():" + bidding.getBidderId()
					+ " title: " + title
					+ " content:" + content);
		} else {
			logger.error("There is no default consultant id.");
		}

		logger.info("save bidding.");
		model.addAttribute("bidding", new Bidding());
		addMessage(redirectAttributes, "update Bidding success.");
		return "redirect:"+Global.getAdminPath()+"/cst/consultant/?repage";
	}
	
	@RequiresPermissions("cst:bidding:edit")
	@RequestMapping(value = "delete")
	public String delete(Bidding bidding, RedirectAttributes redirectAttributes) {
		biddingService.delete(bidding);
		addMessage(redirectAttributes, "删除Bidding成功");
		return "redirect:"+Global.getAdminPath()+"/cst/bidding/?repage";
	}

}