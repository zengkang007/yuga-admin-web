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
import com.yuga.modules.school.entity.base.YgStudentUser;
import com.yuga.modules.school.service.base.YgStudentUserService;

/**
 * 学生信息Controller
 * @author 曾康
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/ygStudentUser")
public class YgStudentUserController extends BaseController {

	@Autowired
	private YgStudentUserService ygStudentUserService;
	
	@ModelAttribute
	public YgStudentUser get(@RequestParam(required=false) String id) {
		YgStudentUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygStudentUserService.get(id);
		}
		if (entity == null){
			entity = new YgStudentUser();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:ygStudentUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgStudentUser ygStudentUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgStudentUser> page = ygStudentUserService.findPage(new Page<YgStudentUser>(request, response), ygStudentUser); 
		model.addAttribute("page", page);
		return "modules/school/base/ygStudentUserList";
	}

	@RequiresPermissions("school:base:ygStudentUser:view")
	@RequestMapping(value = "form")
	public String form(YgStudentUser ygStudentUser, Model model) {
		model.addAttribute("ygStudentUser", ygStudentUser);
		return "modules/school/base/ygStudentUserForm";
	}

	@RequiresPermissions("school:base:ygStudentUser:edit")
	@RequestMapping(value = "save")
	public String save(YgStudentUser ygStudentUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygStudentUser)){
			return form(ygStudentUser, model);
		}
		ygStudentUserService.save(ygStudentUser);
		addMessage(redirectAttributes, "保存学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygStudentUser/?repage";
	}
	
	@RequiresPermissions("school:base:ygStudentUser:edit")
	@RequestMapping(value = "delete")
	public String delete(YgStudentUser ygStudentUser, RedirectAttributes redirectAttributes) {
		ygStudentUserService.delete(ygStudentUser);
		addMessage(redirectAttributes, "删除学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygStudentUser/?repage";
	}

}