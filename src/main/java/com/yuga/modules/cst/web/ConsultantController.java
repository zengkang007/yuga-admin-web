/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.deploy.config.Config;
import com.yuga.common.utils.StringUtil;
import com.yuga.modules.cst.entity.YgConsultantBook;
import com.yuga.modules.cst.registries.BookConst;
import com.yuga.modules.cst.registries.FormStatusConst;
import com.yuga.modules.cst.service.YgConsultantBookService;
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
import com.yuga.modules.cst.entity.Consultant;
import com.yuga.modules.cst.service.ConsultantService;

import java.util.Date;

/**
 * ConsultantController
 * @author zengk
 * @version 2017-01-06
 */
@Controller
@RequestMapping(value = "${adminPath}/cst/consultant")
public class ConsultantController extends BaseController {

	@Autowired
	private ConsultantService consultantService;

	@Autowired
	private YgConsultantBookService bookService;

	@ModelAttribute
	public Consultant get(@RequestParam(required=false) String id) {
		Consultant entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = consultantService.get(id);
		}
		if (entity == null){
			entity = new Consultant();
		}
		return entity;
	}


	@RequiresPermissions("cst:consultant:view")
	@RequestMapping(value = {"list", ""})
	public String list(Consultant consultant, HttpServletRequest request, HttpServletResponse response, Model model) {
		//默认查询审核通过的consultant
		//consultant.setFormStatus(FormStatusConst.PASSED);
		Page<Consultant> qPage = new Page<Consultant>(request, response);
		qPage.setOrderBy("id desc");
		Page<Consultant> page = consultantService.findPage(qPage, consultant);

		model.addAttribute("page", page);
		return "modules/cst/consultantList";
	}

	@RequiresPermissions("cst:consultant:view")
	@RequestMapping(value = {"bookConfirm"})
	public String bookConfirm(Consultant consultant, HttpServletRequest request, HttpServletResponse response, Model model) {
		//订阅跳转页面
		consultant.setFormStatus(FormStatusConst.CONSULTANT_BOOKED);
		Page<Consultant> page = consultantService.findPage(new Page<Consultant>(request, response), consultant);
		model.addAttribute("page", page);
		return "modules/cst/consultantConfirm";
	}

	@RequiresPermissions("cst:consultant:view")
	@RequestMapping(value = {"myConsult"})
	public String myConsultlist(Consultant consultant, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询自己提交的咨询
		consultant.setSubmitter(UserUtils.getUser().getId());
		Page<Consultant> page = consultantService.findMyPage(new Page<Consultant>(request, response), consultant);
		model.addAttribute("page", page);
		return "modules/cst/myConsultantList";
	}

	@RequiresPermissions("cst:consultant:view")
	@RequestMapping(value = {"audit"})
	public String auditList(Consultant consultant, HttpServletRequest request, HttpServletResponse response, Model model) {
		consultant.setFormStatus(FormStatusConst.AUDITING);
		Page<Consultant> page = consultantService.findPage(new Page<Consultant>(request, response), consultant);
		model.addAttribute("page", page);
		return "modules/cst/auditConsultantList";
	}

	@RequiresPermissions("cst:consultant:view")
	@RequestMapping(value = "form")
	public String form(Consultant consultant, Model model) {
		model.addAttribute("consultant", consultant);
		return "modules/cst/consultantForm";
	}

	@RequiresPermissions("cst:consultant:edit")
	@RequestMapping(value = "save")
	public String save(Consultant consultant, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, consultant)){
			return form(consultant, model);
		}
		consultant.setSubmitter( UserUtils.getUser().getId());
		consultant.setCreateDate(new Date());
		consultantService.save(consultant);
		addMessage(redirectAttributes, "Post Consultant success, waiting for approve.");
		return "redirect:"+Global.getAdminPath()+"/cst/consultant/?repage";
	}

	/**
	 * 最终确定预定
	 * @param consultant
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cst:consultant:edit")
	@RequestMapping(value = "book")
	public String book(Consultant consultant, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, consultant)){
			return form(consultant, model);
		}
		bookService.doBook(consultant);
		consultant.setBookUserId(UserUtils.getUser().getId());
		consultant.setIsBook(BookConst.BOOKED);
		consultantService.save(consultant);
		addMessage(redirectAttributes, "Post Consultant success, waiting for approve.");
		return "redirect:"+Global.getAdminPath()+"/cst/consultant/?repage";
	}

	@RequiresPermissions("cst:consultant:edit")
	@RequestMapping(value = "saveAudit")
	public String saveAudit(Consultant consultant, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, consultant)){
			return form(consultant, model);
		}
		consultantService.save(consultant);
		addMessage(redirectAttributes, "Change Consultant status succuessfully.");
		return "redirect:"+Global.getAdminPath()+"/cst/consultant/audit?repage";
	}

	@RequiresPermissions("cst:consultant:edit")
	@RequestMapping(value = "profile")
	public String profile(Consultant consultant, Model model, RedirectAttributes redirectAttributes) {
		User currentUser = null;
		if( consultant.getSubmitter() != null ) {
			logger.info("查询用户信息." + consultant.getSubmitter());
			currentUser = UserUtils.get(consultant.getSubmitter());
		} else {
			logger.error("User Id is empty.");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/userProfile";
	}

	@RequiresPermissions("cst:consultant:edit")
	@RequestMapping(value = "delete")
	public String delete(Consultant consultant, RedirectAttributes redirectAttributes) {
		consultantService.delete(consultant);
		addMessage(redirectAttributes, "删除Consultant成功");
		return "redirect:"+Global.getAdminPath()+"/cst/consultant/?repage";
	}

}