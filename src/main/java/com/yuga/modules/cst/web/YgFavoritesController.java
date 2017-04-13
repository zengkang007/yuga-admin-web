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
import com.yuga.modules.cst.entity.YgFavorites;
import com.yuga.modules.cst.service.YgFavoritesService;

/**
 * FavoritesController
 * @author zengk
 * @version 2017-04-13
 */
@Controller
@RequestMapping(value = "${adminPath}/cst/ygFavorites")
public class YgFavoritesController extends BaseController {

	@Autowired
	private YgFavoritesService ygFavoritesService;
	
	@ModelAttribute
	public YgFavorites get(@RequestParam(required=false) String id) {
		YgFavorites entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygFavoritesService.get(id);
		}
		if (entity == null){
			entity = new YgFavorites();
		}
		return entity;
	}
	
	@RequiresPermissions("cst:ygFavorites:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgFavorites ygFavorites, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgFavorites> page = ygFavoritesService.findPage(new Page<YgFavorites>(request, response), ygFavorites); 
		model.addAttribute("page", page);
		return "modules/cst/ygFavoritesList";
	}

	@RequiresPermissions("cst:ygFavorites:view")
	@RequestMapping(value = "form")
	public String form(YgFavorites ygFavorites, Model model) {
		model.addAttribute("ygFavorites", ygFavorites);
		return "modules/cst/ygFavoritesForm";
	}

	@RequiresPermissions("cst:ygFavorites:edit")
	@RequestMapping(value = "save")
	public String save(YgFavorites ygFavorites, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygFavorites)){
			return form(ygFavorites, model);
		}
		ygFavoritesService.save(ygFavorites);
		addMessage(redirectAttributes, "保存Favorites成功");
		return "redirect:"+Global.getAdminPath()+"/cst/ygFavorites/?repage";
	}
	
	@RequiresPermissions("cst:ygFavorites:edit")
	@RequestMapping(value = "delete")
	public String delete(YgFavorites ygFavorites, RedirectAttributes redirectAttributes) {
		ygFavoritesService.delete(ygFavorites);
		addMessage(redirectAttributes, "删除Favorites成功");
		return "redirect:"+Global.getAdminPath()+"/cst/ygFavorites/?repage";
	}

}