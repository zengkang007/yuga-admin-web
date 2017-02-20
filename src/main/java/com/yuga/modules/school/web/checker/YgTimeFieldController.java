/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.web.checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.yuga.modules.school.entity.checker.YgTimeField;
import com.yuga.modules.school.service.checker.YgTimeFieldService;

/**
 * 巡检子表Controller
 * @author 曾康
 * @version 2016-10-26
 */
@Controller
@RequestMapping(value = "${adminPath}/school/checker/ygTimeField")
public class YgTimeFieldController extends BaseController {

	@Autowired
	private YgTimeFieldService ygTimeFieldService;
	
	@ModelAttribute
	public YgTimeField get(@RequestParam(required=false) String id) {
		YgTimeField entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygTimeFieldService.get(id);
		}
		if (entity == null){
			entity = new YgTimeField();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(YgTimeField ygTimeField, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgTimeField> page = ygTimeFieldService.findPage(new Page<YgTimeField>(request, response), ygTimeField); 
		model.addAttribute("page", page);
		return "modules/school/checker/ygTimeFieldList";
	}

	@RequestMapping(value = "form")
	public String form(YgTimeField ygTimeField, Model model) {
		model.addAttribute("ygTimeField", ygTimeField);
		return "modules/school/checker/ygTimeFieldForm";
	}

	@RequestMapping(value = "save")
	public String save(YgTimeField ygTimeField, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygTimeField)){
			return form(ygTimeField, model);
		}
		ygTimeFieldService.save(ygTimeField);
		addMessage(redirectAttributes, "保存巡检子表成功");
		return "redirect:"+Global.getAdminPath()+"/school/checker/ygTimeField/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(YgTimeField ygTimeField, RedirectAttributes redirectAttributes) {
		ygTimeFieldService.delete(ygTimeField);
		addMessage(redirectAttributes, "删除巡检子表成功");
		return "redirect:"+Global.getAdminPath()+"/school/checker/ygTimeField/?repage";
	}
}