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
import com.yuga.common.utils.StringUtils;
import com.yuga.common.web.BaseController;
import com.yuga.modules.school.entity.base.YgGrade;
import com.yuga.modules.school.service.base.YgGradeService;

/**
 * 年级级别Controller
 * @author 曾康
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/ygGrade")
public class YgGradeController extends BaseController {

	@Autowired
	private YgGradeService ygGradeService;
	
	@ModelAttribute
	public YgGrade get(@RequestParam(required=false) String id) {
		YgGrade entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygGradeService.get(id);
		}
		if (entity == null){
			entity = new YgGrade();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:ygGrade:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgGrade ygGrade, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgGrade> page = ygGradeService.findPage(new Page<YgGrade>(request, response), ygGrade); 
		model.addAttribute("page", page);
		return "modules/school/base/ygGradeList";
	}

	@RequiresPermissions("school:base:ygGrade:view")
	@RequestMapping(value = "form")
	public String form(YgGrade ygGrade, Model model) {
		model.addAttribute("ygGrade", ygGrade);
		return "modules/school/base/ygGradeForm";
	}

	@RequiresPermissions("school:base:ygGrade:edit")
	@RequestMapping(value = "save")
	public String save(YgGrade ygGrade, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygGrade)){
			return form(ygGrade, model);
		}
		ygGradeService.save(ygGrade);
		addMessage(redirectAttributes, "保存年级级别成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygGrade/?repage";
	}
	
	@RequiresPermissions("school:base:ygGrade:edit")
	@RequestMapping(value = "delete")
	public String delete(YgGrade ygGrade, RedirectAttributes redirectAttributes) {
		ygGradeService.delete(ygGrade);
		addMessage(redirectAttributes, "删除年级级别成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygGrade/?repage";
	}

}