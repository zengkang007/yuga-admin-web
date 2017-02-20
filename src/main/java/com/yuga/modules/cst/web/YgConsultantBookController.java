/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuga.modules.cst.registries.RespCodeConst;
import com.yuga.modules.cst.registries.RespDTO;
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
import com.yuga.common.web.BaseController;
import com.yuga.common.utils.StringUtils;
import com.yuga.modules.cst.entity.YgConsultantBook;
import com.yuga.modules.cst.service.YgConsultantBookService;

/**
 * consultant bookingController
 * @author 曾康
 * @version 2017-02-16
 */
@Controller
@RequestMapping(value = "${adminPath}/cst/ygConsultantBook")
public class YgConsultantBookController extends BaseController {

	@Autowired
	private YgConsultantBookService bookService;
	
	@ModelAttribute
	public YgConsultantBook get(@RequestParam(required=false) String id) {
		YgConsultantBook entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bookService.get(id);
		}
		if (entity == null){
			entity = new YgConsultantBook();
		}
		return entity;
	}


	/**
	 *  顾问接受预定
	 */
	@RequestMapping(value = "acceptRequest")
	@ResponseBody
	public RespDTO acceptRequest(String consultantId, Model model) {
		RespDTO dto = new RespDTO(RespCodeConst.ERROR, RespCodeConst.ERROR_MESSAGE);
		if (StringUtils.isNotBlank(consultantId)){
			bookService.acceptRequest(consultantId);
			dto = new RespDTO(RespCodeConst.SUCCESS, RespCodeConst.ERROR_MESSAGE);
		}
		return dto;
	}

	@RequiresPermissions("cst:ygConsultantBook:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgConsultantBook ygConsultantBook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgConsultantBook> page = bookService.findPage(new Page<YgConsultantBook>(request, response), ygConsultantBook);
		model.addAttribute("page", page);
		return "modules/cst/ygConsultantBookList";
	}

	@RequiresPermissions("cst:ygConsultantBook:view")
	@RequestMapping(value = "form")
	public String form(YgConsultantBook ygConsultantBook, Model model) {
		model.addAttribute("ygConsultantBook", ygConsultantBook);
		return "modules/cst/ygConsultantBookForm";
	}

	@RequiresPermissions("cst:ygConsultantBook:edit")
	@RequestMapping(value = "save")
	public String save(YgConsultantBook ygConsultantBook, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygConsultantBook)){
			return form(ygConsultantBook, model);
		}
		bookService.save(ygConsultantBook);
		addMessage(redirectAttributes, "保存consultant booking成功");
		return "redirect:"+Global.getAdminPath()+"/cst/ygConsultantBook/?repage";
	}
	
	@RequiresPermissions("cst:ygConsultantBook:edit")
	@RequestMapping(value = "delete")
	public String delete(YgConsultantBook ygConsultantBook, RedirectAttributes redirectAttributes) {
		bookService.delete(ygConsultantBook);
		addMessage(redirectAttributes, "删除consultant booking成功");
		return "redirect:"+Global.getAdminPath()+"/cst/ygConsultantBook/?repage";
	}

}