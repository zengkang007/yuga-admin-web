/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.web.base;

import java.util.Date;

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
import com.yuga.modules.school.entity.base.YgClass;
import com.yuga.modules.school.service.base.YgClassService;

/**
 * 班级信息Controller
 * @author 曾康
 * @version 2016-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/ygClass")
public class YgClassController extends BaseController {

	@Autowired
	private YgClassService ygClassService;
	
	@ModelAttribute
	public YgClass get(@RequestParam(required=false) String id) {
		YgClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygClassService.get(id);
		}
		if (entity == null){
			entity = new YgClass();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:ygClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgClass ygClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgClass> page = ygClassService.findPage(new Page<YgClass>(request, response), ygClass); 
		model.addAttribute("page", page);
		return "modules/school/base/ygClassList";
	}

	@RequiresPermissions("school:base:ygClass:view")
	@RequestMapping(value = "form")
	public String form(YgClass ygClass, Model model) {
		model.addAttribute("ygClass", ygClass);
		return "modules/school/base/ygClassForm";
	}

	@RequiresPermissions("school:base:ygClass:edit")
	@RequestMapping(value = "save")
	public String save(YgClass ygClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygClass)){
			return form(ygClass, model);
		}
		ygClass.setCreateTime(new Date());
		ygClassService.save(ygClass);
		addMessage(redirectAttributes, "保存班级信息成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygClass/?repage";
	}
	
	@RequiresPermissions("school:base:ygClass:edit")
	@RequestMapping(value = "delete")
	public String delete(YgClass ygClass, RedirectAttributes redirectAttributes) {
		ygClassService.delete(ygClass);
		addMessage(redirectAttributes, "删除班级信息成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygClass/?repage";
	}

}