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
import com.yuga.modules.school.entity.base.YgTeacherUser;
import com.yuga.modules.school.service.base.YgTeacherUserService;

/**
 * 教师信息Controller
 * @author 曾康
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/ygTeacherUser")
public class YgTeacherUserController extends BaseController {

	@Autowired
	private YgTeacherUserService ygTeacherUserService;
	
	@ModelAttribute
	public YgTeacherUser get(@RequestParam(required=false) String id) {
		YgTeacherUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygTeacherUserService.get(id);
		}
		if (entity == null){
			entity = new YgTeacherUser();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:ygTeacherUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgTeacherUser ygTeacherUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgTeacherUser> page = ygTeacherUserService.findPage(new Page<YgTeacherUser>(request, response), ygTeacherUser); 
		model.addAttribute("page", page);
		return "modules/school/base/ygTeacherUserList";
	}

	@RequiresPermissions("school:base:ygTeacherUser:view")
	@RequestMapping(value = "form")
	public String form(YgTeacherUser ygTeacherUser, Model model) {
		model.addAttribute("ygTeacherUser", ygTeacherUser);
		return "modules/school/base/ygTeacherUserForm";
	}

	@RequiresPermissions("school:base:ygTeacherUser:edit")
	@RequestMapping(value = "save")
	public String save(YgTeacherUser ygTeacherUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygTeacherUser)){
			return form(ygTeacherUser, model);
		}
		ygTeacherUserService.save(ygTeacherUser);
		addMessage(redirectAttributes, "保存教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygTeacherUser/?repage";
	}
	
	@RequiresPermissions("school:base:ygTeacherUser:edit")
	@RequestMapping(value = "delete")
	public String delete(YgTeacherUser ygTeacherUser, RedirectAttributes redirectAttributes) {
		ygTeacherUserService.delete(ygTeacherUser);
		addMessage(redirectAttributes, "删除教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygTeacherUser/?repage";
	}

}