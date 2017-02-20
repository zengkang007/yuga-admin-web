/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.yuga.modules.cst.entity.YgNotification;
import com.yuga.modules.cst.service.YgNotificationService;

/**
 * NotificationController
 * @author 曾康
 * @version 2017-02-16
 */
@Controller
@RequestMapping(value = "${adminPath}/cst/ygNotification")
public class YgNotificationController extends BaseController {

	@Autowired
	private YgNotificationService ygNotificationService;
	
	@ModelAttribute
	public YgNotification get(@RequestParam(required=false) String id) {
		YgNotification entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygNotificationService.get(id);
		}
		if (entity == null){
			entity = new YgNotification();
		}
		return entity;
	}
	
	@RequiresPermissions("cst:ygNotification:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgNotification ygNotification, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgNotification> page = ygNotificationService.findPage(new Page<YgNotification>(request, response), ygNotification); 
		model.addAttribute("page", page);
		return "modules/cst/ygNotificationList";
	}

	@RequiresPermissions("cst:ygNotification:view")
	@RequestMapping(value = "form")
	public String form(YgNotification ygNotification, Model model) {
		model.addAttribute("ygNotification", ygNotification);
		return "modules/cst/ygNotificationForm";
	}

	@RequiresPermissions("cst:ygNotification:edit")
	@RequestMapping(value = "save")
	public String save(YgNotification ygNotification, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygNotification)){
			return form(ygNotification, model);
		}
		ygNotificationService.save(ygNotification);
		addMessage(redirectAttributes, "保存Notification成功");
		return "redirect:"+Global.getAdminPath()+"/cst/ygNotification/?repage";
	}
	
	@RequiresPermissions("cst:ygNotification:edit")
	@RequestMapping(value = "delete")
	public String delete(YgNotification ygNotification, RedirectAttributes redirectAttributes) {
		ygNotificationService.delete(ygNotification);
		addMessage(redirectAttributes, "删除Notification成功");
		return "redirect:"+Global.getAdminPath()+"/cst/ygNotification/?repage";
	}

}