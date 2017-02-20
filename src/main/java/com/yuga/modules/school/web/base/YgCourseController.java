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
import com.yuga.modules.school.entity.base.YgCourse;
import com.yuga.modules.school.service.base.YgCourseService;

/**
 * 课程Controller
 * @author 曾康
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/ygCourse")
public class YgCourseController extends BaseController {

	@Autowired
	private YgCourseService ygCourseService;
	
	@ModelAttribute
	public YgCourse get(@RequestParam(required=false) String id) {
		YgCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygCourseService.get(id);
		}
		if (entity == null){
			entity = new YgCourse();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:ygCourse:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgCourse ygCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgCourse> page = ygCourseService.findPage(new Page<YgCourse>(request, response), ygCourse); 
		model.addAttribute("page", page);
		return "modules/school/base/ygCourseList";
	}

	@RequiresPermissions("school:base:ygCourse:view")
	@RequestMapping(value = "form")
	public String form(YgCourse ygCourse, Model model) {
		model.addAttribute("ygCourse", ygCourse);
		return "modules/school/base/ygCourseForm";
	}

	@RequiresPermissions("school:base:ygCourse:edit")
	@RequestMapping(value = "save")
	public String save(YgCourse ygCourse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygCourse)){
			return form(ygCourse, model);
		}
		ygCourseService.save(ygCourse);
		addMessage(redirectAttributes, "保存课程成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygCourse/?repage";
	}
	
	@RequiresPermissions("school:base:ygCourse:edit")
	@RequestMapping(value = "delete")
	public String delete(YgCourse ygCourse, RedirectAttributes redirectAttributes) {
		ygCourseService.delete(ygCourse);
		addMessage(redirectAttributes, "删除课程成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygCourse/?repage";
	}

}