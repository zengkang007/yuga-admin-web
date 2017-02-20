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
import com.yuga.modules.school.entity.base.YgMultimediaStatus;
import com.yuga.modules.school.service.base.YgMultimediaStatusService;

/**
 * 多媒体使用情况Controller
 * @author 曾康
 * @version 2016-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/school/base/ygMultimediaStatus")
public class YgMultimediaStatusController extends BaseController {

	@Autowired
	private YgMultimediaStatusService ygMultimediaStatusService;
	
	@ModelAttribute
	public YgMultimediaStatus get(@RequestParam(required=false) String id) {
		YgMultimediaStatus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygMultimediaStatusService.get(id);
		}
		if (entity == null){
			entity = new YgMultimediaStatus();
		}
		return entity;
	}
	
	@RequiresPermissions("school:base:ygMultimediaStatus:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgMultimediaStatus ygMultimediaStatus, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgMultimediaStatus> page = ygMultimediaStatusService.findPage(new Page<YgMultimediaStatus>(request, response), ygMultimediaStatus); 
		model.addAttribute("page", page);
		return "modules/school/base/ygMultimediaStatusList";
	}

	@RequiresPermissions("school:base:ygMultimediaStatus:view")
	@RequestMapping(value = "form")
	public String form(YgMultimediaStatus ygMultimediaStatus, Model model) {
		model.addAttribute("ygMultimediaStatus", ygMultimediaStatus);
		return "modules/school/base/ygMultimediaStatusForm";
	}

	@RequiresPermissions("school:base:ygMultimediaStatus:edit")
	@RequestMapping(value = "save")
	public String save(YgMultimediaStatus ygMultimediaStatus, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygMultimediaStatus)){
			return form(ygMultimediaStatus, model);
		}
		ygMultimediaStatusService.save(ygMultimediaStatus);
		addMessage(redirectAttributes, "保存多媒体使用情况成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygMultimediaStatus/?repage";
	}
	
	@RequiresPermissions("school:base:ygMultimediaStatus:edit")
	@RequestMapping(value = "delete")
	public String delete(YgMultimediaStatus ygMultimediaStatus, RedirectAttributes redirectAttributes) {
		ygMultimediaStatusService.delete(ygMultimediaStatus);
		addMessage(redirectAttributes, "删除多媒体使用情况成功");
		return "redirect:"+Global.getAdminPath()+"/school/base/ygMultimediaStatus/?repage";
	}

}