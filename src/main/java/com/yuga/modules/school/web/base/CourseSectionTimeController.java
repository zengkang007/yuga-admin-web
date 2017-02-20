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
import com.yuga.modules.school.entity.base.CourseSectionTime;
import com.yuga.modules.school.service.base.CourseSectionTimeService;

/**
 * 节次时间Controller
 * @author 曾康
 * @version 2016-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/courseSectionTime")
public class CourseSectionTimeController extends BaseController {

	@Autowired
	private CourseSectionTimeService courseSectionTimeService;
	
	@ModelAttribute
	public CourseSectionTime get(@RequestParam(required=false) String id) {
		CourseSectionTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseSectionTimeService.get(id);
		}
		if (entity == null){
			entity = new CourseSectionTime();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:courseSectionTime:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseSectionTime courseSectionTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseSectionTime> page = courseSectionTimeService.findPage(new Page<CourseSectionTime>(request, response), courseSectionTime); 
		model.addAttribute("page", page);
		return "modules/school/base/courseSectionTimeList";
	}

	@RequiresPermissions("school:base:courseSectionTime:view")
	@RequestMapping(value = "form")
	public String form(CourseSectionTime courseSectionTime, Model model) {
		model.addAttribute("courseSectionTime", courseSectionTime);
		return "modules/school/base/courseSectionTimeForm";
	}

	@RequiresPermissions("school:base:courseSectionTime:edit")
	@RequestMapping(value = "save")
	public String save(CourseSectionTime courseSectionTime, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseSectionTime)){
			return form(courseSectionTime, model);
		}
		courseSectionTimeService.save(courseSectionTime);
		addMessage(redirectAttributes, "保存节次时间成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/courseSectionTime/?repage";
	}
	
	@RequiresPermissions("school:base:courseSectionTime:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseSectionTime courseSectionTime, RedirectAttributes redirectAttributes) {
		courseSectionTimeService.delete(courseSectionTime);
		addMessage(redirectAttributes, "删除节次时间成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/courseSectionTime/?repage";
	}

}