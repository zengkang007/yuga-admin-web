/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.web.base;

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
import com.yuga.modules.school.entity.base.YgStudentStatus;
import com.yuga.modules.school.service.base.YgStudentStatusService;

/**
 * 学生学习情况Controller
 * @author 曾康
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/ygStudentStatus")
public class YgStudentStatusController extends BaseController {

	@Autowired
	private YgStudentStatusService ygStudentStatusService;
	
	@ModelAttribute
	public YgStudentStatus get(@RequestParam(required=false) String id) {
		YgStudentStatus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygStudentStatusService.get(id);
		}
		if (entity == null){
			entity = new YgStudentStatus();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:ygStudentStatus:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgStudentStatus ygStudentStatus, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgStudentStatus> page = ygStudentStatusService.findPage(new Page<YgStudentStatus>(request, response), ygStudentStatus); 
		model.addAttribute("page", page);
		return "modules/school/base/ygStudentStatusList";
	}

	@RequiresPermissions("school:base:ygStudentStatus:view")
	@RequestMapping(value = "form")
	public String form(YgStudentStatus ygStudentStatus, Model model) {
		model.addAttribute("ygStudentStatus", ygStudentStatus);
		return "modules/school/base/ygStudentStatusForm";
	}

	@RequiresPermissions("school:base:ygStudentStatus:edit")
	@RequestMapping(value = "save")
	public String save(YgStudentStatus ygStudentStatus, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygStudentStatus)){
			return form(ygStudentStatus, model);
		}
		ygStudentStatusService.save(ygStudentStatus);
		addMessage(redirectAttributes, "保存学生学习情况成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygStudentStatus/?repage";
	}
	
	@RequiresPermissions("school:base:ygStudentStatus:edit")
	@RequestMapping(value = "delete")
	public String delete(YgStudentStatus ygStudentStatus, RedirectAttributes redirectAttributes) {
		ygStudentStatusService.delete(ygStudentStatus);
		addMessage(redirectAttributes, "删除学生学习情况成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygStudentStatus/?repage";
	}

}