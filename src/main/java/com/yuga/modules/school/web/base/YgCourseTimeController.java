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
import com.yuga.modules.school.entity.base.YgCourseTime;
import com.yuga.modules.school.service.base.YgCourseTimeService;

/**
 * 课时维护Controller
 * @author 曾康
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/ygCourseTime")
public class YgCourseTimeController extends BaseController {

	@Autowired
	private YgCourseTimeService ygCourseTimeService;
	
	@ModelAttribute
	public YgCourseTime get(@RequestParam(required=false) String id) {
		YgCourseTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygCourseTimeService.get(id);
		}
		if (entity == null){
			entity = new YgCourseTime();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:ygCourseTime:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgCourseTime ygCourseTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgCourseTime> page = ygCourseTimeService.findPage(new Page<YgCourseTime>(request, response), ygCourseTime); 
		model.addAttribute("page", page);
		return "modules/school/base/ygCourseTimeList";
	}

	@RequiresPermissions("school:base:ygCourseTime:view")
	@RequestMapping(value = "form")
	public String form(YgCourseTime ygCourseTime, Model model) {
		model.addAttribute("ygCourseTime", ygCourseTime);
		return "modules/school/base/ygCourseTimeForm";
	}

	@RequiresPermissions("school:base:ygCourseTime:edit")
	@RequestMapping(value = "save")
	public String save(YgCourseTime ygCourseTime, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygCourseTime)){
			return form(ygCourseTime, model);
		}
		ygCourseTimeService.save(ygCourseTime);
		addMessage(redirectAttributes, "保存课时维护成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygCourseTime/?repage";
	}
	
	@RequiresPermissions("school:base:ygCourseTime:edit")
	@RequestMapping(value = "delete")
	public String delete(YgCourseTime ygCourseTime, RedirectAttributes redirectAttributes) {
		ygCourseTimeService.delete(ygCourseTime);
		addMessage(redirectAttributes, "删除课时维护成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygCourseTime/?repage";
	}

}