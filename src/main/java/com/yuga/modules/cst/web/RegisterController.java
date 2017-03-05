/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.web;

import com.google.common.collect.Lists;
import com.yuga.common.config.Global;
import com.yuga.common.utils.IdGen;
import com.yuga.common.utils.StringUtil;
import com.yuga.common.utils.StringUtils;
import com.yuga.common.web.BaseController;
import com.yuga.modules.sys.dao.RoleDao;
import com.yuga.modules.sys.entity.Role;
import com.yuga.modules.sys.entity.User;
import com.yuga.modules.sys.service.SystemService;
import com.yuga.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * ConsultantController
 * @author zengk
 * @version 2017-01-06
 */
@Controller
@RequestMapping(value = "/b")
public class RegisterController extends BaseController {

	@Autowired
	private SystemService systemService;

	@Autowired
	private RoleDao roleDao;


	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/userRegister";
	}

	@RequestMapping(value = {"register"})
	public String register(User user, Model model, RedirectAttributes redirectAttributes) {
		logger.info("注册用户信息.");
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		}
//		if (!beanValidator(model, user)){
//			return form(user, model);
//		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "save '" + user.getLoginName() + "' failure，login name exist.");
			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (String id : roleIdList){
			roleList.add(roleDao.get(id));
		}
		//设置默认角色
		//默认普通用户
		String roleId = Global.getConfig("default.role.id");
//		if(StringUtils.isNotEmpty(roleId)){
//			logger.info("The default role id is : " + roleId);
//			Role role = roleDao.get(roleId);
//			roleList.add(role);
			user.setCreateDate(new Date());
			user.setRoleList(roleList);
			// 保存用户信息
			//user.setId(IdGen.uuid());
			//user.setIsNewRecord(true);
			systemService.saveUser(user);
			// 清除当前用户缓存
			if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
				UserUtils.clearCache();
				//UserUtils.getCacheMap().clear();
			}
			addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
//		} else {
//			logger.error("There is no default role id .");
//		}

		return "modules/cst/registerConfirm";
	}

	private String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	private String form(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/userRegister";
	}
}