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
import com.yuga.modules.school.entity.base.YgTeacherStatus;
import com.yuga.modules.school.service.base.YgTeacherStatusService;

/**
 * 教师教学情况Controller
 * @author 曾康
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/ygTeacherStatus")
public class YgTeacherStatusController extends BaseController {

	@Autowired
	private YgTeacherStatusService ygTeacherStatusService;
	
	@ModelAttribute
	public YgTeacherStatus get(@RequestParam(required=false) String id) {
		YgTeacherStatus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygTeacherStatusService.get(id);
		}
		if (entity == null){
			entity = new YgTeacherStatus();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:ygTeacherStatus:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgTeacherStatus ygTeacherStatus, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgTeacherStatus> page = ygTeacherStatusService.findPage(new Page<YgTeacherStatus>(request, response), ygTeacherStatus); 
		model.addAttribute("page", page);
		return "modules/school/base/ygTeacherStatusList";
	}

	@RequiresPermissions("school:base:ygTeacherStatus:view")
	@RequestMapping(value = "form")
	public String form(YgTeacherStatus ygTeacherStatus, Model model) {
		model.addAttribute("ygTeacherStatus", ygTeacherStatus);
		return "modules/school/base/ygTeacherStatusForm";
	}

	@RequiresPermissions("school:base:ygTeacherStatus:edit")
	@RequestMapping(value = "save")
	public String save(YgTeacherStatus ygTeacherStatus, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygTeacherStatus)){
			return form(ygTeacherStatus, model);
		}
		ygTeacherStatusService.save(ygTeacherStatus);
		addMessage(redirectAttributes, "保存教师教学情况成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygTeacherStatus/?repage";
	}
	
	@RequiresPermissions("school:base:ygTeacherStatus:edit")
	@RequestMapping(value = "delete")
	public String delete(YgTeacherStatus ygTeacherStatus, RedirectAttributes redirectAttributes) {
		ygTeacherStatusService.delete(ygTeacherStatus);
		addMessage(redirectAttributes, "删除教师教学情况成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygTeacherStatus/?repage";
	}

}