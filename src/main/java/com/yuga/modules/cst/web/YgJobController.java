/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.web;

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
import com.yuga.modules.cst.entity.YgJob;
import com.yuga.modules.cst.service.YgJobService;

/**
 * JobController
 * @author zengk
 * @version 2017-03-04
 */
@Controller
@RequestMapping(value = "${adminPath}/cst/job")
public class YgJobController extends BaseController {

	@Autowired
	private YgJobService ygJobService;
	
	@ModelAttribute
	public YgJob get(@RequestParam(required=false) String id) {
		YgJob entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygJobService.get(id);
		}
		if (entity == null){
			entity = new YgJob();
		}
		return entity;
	}
	
	@RequiresPermissions("cst:ygJob:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgJob ygJob, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgJob> page = ygJobService.findPage(new Page<YgJob>(request, response), ygJob); 
		model.addAttribute("page", page);
		return "modules/cst/ygJobList";
	}

	@RequiresPermissions("cst:ygJob:view")
	@RequestMapping(value = "form")
	public String form(YgJob ygJob, Model model) {
		model.addAttribute("ygJob", ygJob);
		return "modules/cst/ygJobForm";
	}

	@RequiresPermissions("cst:ygJob:edit")
	@RequestMapping(value = "save")
	public String save(YgJob ygJob, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygJob)){
			return form(ygJob, model);
		}
		ygJobService.save(ygJob);
		addMessage(redirectAttributes, "保存Job成功");
		return "redirect:"+Global.getAdminPath()+"/cst/job/?repage";
	}
	
	@RequiresPermissions("cst:ygJob:edit")
	@RequestMapping(value = "delete")
	public String delete(YgJob ygJob, RedirectAttributes redirectAttributes) {
		ygJobService.delete(ygJob);
		addMessage(redirectAttributes, "删除Job成功");
		return "redirect:"+Global.getAdminPath()+"/cst/job/?repage";
	}

}