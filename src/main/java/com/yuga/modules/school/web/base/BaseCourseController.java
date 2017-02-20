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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuga.common.config.Global;
import com.yuga.common.persistence.Page;
import com.yuga.common.utils.StringUtils;
import com.yuga.common.web.BaseController;
import com.yuga.modules.school.entity.base.BaseCourse;
import com.yuga.modules.school.service.base.BaseCourseService;

/**
 * 课表基础信息Controller
 * @author 曾康
 * @version 2016-10-26
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/baseCourse")
public class BaseCourseController extends BaseController {

	@Autowired
	private BaseCourseService baseCourseService;
	
	@ModelAttribute
	public BaseCourse get(@RequestParam(required=false) String id) {
		BaseCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = baseCourseService.get(id);
		}
		if (entity == null){
			entity = new BaseCourse();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:baseCourse:view")
	@RequestMapping(value = {"list", ""})
	public String list(BaseCourse baseCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BaseCourse> page = baseCourseService.findPage(new Page<BaseCourse>(request, response), baseCourse); 
		model.addAttribute("page", page);
		return "modules/school/base/baseCourseList";
	}

	/**
	 * 查询课表信息
	 * @param baseCourse
	 * @return
	 */
	@RequestMapping(value = "getCourseInfo")
	@ResponseBody
	public BaseCourse getCourseInfo(BaseCourse baseCourse) {
		BaseCourse ret = baseCourseService.getCourseInfo(baseCourse);
		return ret;
	}
	
	@RequiresPermissions("school:base:baseCourse:view")
	@RequestMapping(value = "form")
	public String form(BaseCourse baseCourse, Model model) {
		model.addAttribute("baseCourse", baseCourse);
		return "modules/school/base/baseCourseForm";
	}

	@RequiresPermissions("school:base:baseCourse:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(BaseCourse baseCourse, Model model) {
		model.addAttribute("baseCourse", baseCourse);
		return "modules/school/base/baseCourseCheckForm";
	}
	
	@RequiresPermissions("school:base:baseCourse:edit")
	@RequestMapping(value = "save")
	public String save(BaseCourse baseCourse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, baseCourse)){
			return form(baseCourse, model);
		}
		baseCourseService.save(baseCourse);
		addMessage(redirectAttributes, "保存课表基础信息成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/baseCourse/?repage";
	}
	
	@RequiresPermissions("school:base:baseCourse:edit")
	@RequestMapping(value = "delete")
	public String delete(BaseCourse baseCourse, RedirectAttributes redirectAttributes) {
		baseCourseService.delete(baseCourse);
		addMessage(redirectAttributes, "删除课表基础信息成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/baseCourse/?repage";
	}

}