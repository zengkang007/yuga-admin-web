/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.web.checker;

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
import com.yuga.modules.school.entity.checker.YgTimeCheck;
import com.yuga.modules.school.service.checker.YgTimeCheckService;

/**
 * 巡检时间主表Controller
 * @author 曾康
 * @version 2016-10-26
 */
@Controller
@RequestMapping(value = "${adminPath}/school/checker/ygTimeCheck")
public class YgTimeCheckController extends BaseController {

	@Autowired
	private YgTimeCheckService ygTimeCheckService;
	
	@ModelAttribute
	public YgTimeCheck get(@RequestParam(required=false) String id) {
		YgTimeCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygTimeCheckService.get(id);
		}
		if (entity == null){
			entity = new YgTimeCheck();
		}
		return entity;
	}
	
	@RequiresPermissions("school:checker:ygTimeCheck:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgTimeCheck ygTimeCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgTimeCheck> page = ygTimeCheckService.findPage(new Page<YgTimeCheck>(request, response), ygTimeCheck); 
		model.addAttribute("page", page);
		return "modules/school/checker/ygTimeCheckList";
	}

	/*@RequiresPermissions("school:checker:ygTimeCheck:view")*/
	@RequestMapping(value = "form")
	public String form(YgTimeCheck ygTimeCheck, Model model) {
		model.addAttribute("ygTimeCheck", ygTimeCheck);
		return "modules/school/checker/ygTimeCheckForm";
	}

	@RequiresPermissions("school:checker:ygTimeCheck:edit")
	@RequestMapping(value = "save")
	public String save(YgTimeCheck ygTimeCheck, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygTimeCheck)){
			return form(ygTimeCheck, model);
		}
		ygTimeCheckService.save(ygTimeCheck);
		addMessage(redirectAttributes, "保存巡检时间主表成功");
		return "redirect:"+Global.getAdminPath()+"/school/checker/ygTimeCheck/?repage";
	}
	
	@RequiresPermissions("school:checker:ygTimeCheck:edit")
	@RequestMapping(value = "delete")
	public String delete(YgTimeCheck ygTimeCheck, RedirectAttributes redirectAttributes) {
		ygTimeCheckService.delete(ygTimeCheck);
		addMessage(redirectAttributes, "删除巡检时间主表成功");
		return "redirect:"+Global.getAdminPath()+"/school/checker/ygTimeCheck/?repage";
	}

}