/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.web.checker;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yuga.modules.school.entity.base.CourseSectionTime;
import com.yuga.modules.school.entity.checker.YgClassCheck;
import com.yuga.modules.school.entity.checker.YgTimeCheck;
import com.yuga.modules.school.service.base.BaseCourseService;
import com.yuga.modules.school.service.checker.YgClassCheckService;
import com.yuga.modules.school.service.checker.YgTimeCheckService;
import com.yuga.modules.school.web.base.ExportExcelController;
import com.yuga.modules.sys.entity.User;

/**
 * 巡查情况Controller
 * @author 曾康
 * @version 2016-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/school/checker/ygClassCheck")
public class YgClassCheckController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(YgClassCheckController.class);

	
	@Autowired
	private YgClassCheckService ygClassCheckService;
	
	@Autowired
	private YgTimeCheckService timeCheckService;
	
	@Autowired
	private BaseCourseService baseCourseService;
	
	@ModelAttribute
	public YgClassCheck get(@RequestParam(required=false) String id) {
		YgClassCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ygClassCheckService.get(id);
		}
		if (entity == null){
			entity = new YgClassCheck();
		}
		return entity;
	}
	
	@RequiresPermissions("school:checker:ygClassCheck:view")
	@RequestMapping(value = {"list", ""})
	public String list(YgClassCheck ygClassCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YgClassCheck> page = ygClassCheckService.findPage(new Page<YgClassCheck>(request, response), ygClassCheck); 
		model.addAttribute("page", page);
		return "modules/school/checker/ygClassCheckList";
	}
	
	@RequestMapping(value = {"showclass"})
	public String showclass() {
		return "modules/school/base/ygShowClass";
	}
	
	@RequiresPermissions("school:checker:ygClassCheck:view")
	@RequestMapping(value = {"dowload"})
	public String dowload(YgClassCheck ygClassCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("0000000000");
		List<YgClassCheck> list1 =   ygClassCheckService.findList(ygClassCheck); 
		
		System.out.println("11111111111111");
		ExportExcelController excel= new ExportExcelController();
		System.out.println("222222222");
		excel.Download(list1);
		System.out.println("3333333333333");
		Page<YgClassCheck> page = ygClassCheckService.findPage(new Page<YgClassCheck>(request, response), ygClassCheck); 
		model.addAttribute("page", page);
		return "modules/school/checker/ygClassCheckList";
	}	
	
	
	@RequiresPermissions("school:checker:ygClassCheck:view")
	@RequestMapping(value = "form")
	public String form(YgClassCheck ygClassCheck, Model model) {
		YgTimeCheck timeCheck = timeCheckService.getExtraSelectFields();
		//如果是初始化，则默认选中
		if(ygClassCheck.getId() == null) {
			log.info("初始化系统...");
			ygClassCheck.setCheckItems( timeCheckService.getInitTimeFields() );
		}
		
		if(timeCheck != null) {
			log.info("当前方案是：" + timeCheck.getName());
			ygClassCheck.setTimeCheck(timeCheck);
		} else{
			log.error("没有找到指定的方案。");
		}
		log.info("查询课程信息");
		CourseSectionTime selectionTime = baseCourseService.getCourseSection();
		String weekId = baseCourseService.getConvertWeekId();
		
		if(selectionTime != null) {
			log.info("查询到节次信息:" + selectionTime);
			ygClassCheck.setSectionId(selectionTime.getId());
		}
		
		log.info("查询到周信息:" + weekId);
		ygClassCheck.setWeekId(weekId);
		
		model.addAttribute("ygClassCheck", ygClassCheck);
		return "modules/school/checker/ygClassCheckForm";
	}

	@RequiresPermissions("school:checker:ygClassCheck:edit")
	@RequestMapping(value = "save")
	public String save(YgClassCheck ygClassCheck, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ygClassCheck)){
			return form(ygClassCheck, model);
		}
		User user = new User();
		user.setId(ygClassCheck.getTeacherId());
		ygClassCheck.setUser(user);
		
		YgTimeCheck timeCheck = new YgTimeCheck();
		timeCheck.setId(""+ygClassCheck.getCheckTimeId());
		ygClassCheck.setTimeCheck(timeCheck);
		
		ygClassCheck.setCheckTime(new Date());
		ygClassCheckService.save(ygClassCheck);
		addMessage(redirectAttributes, "保存巡查情况成功");
		return "redirect:"+Global.getAdminPath()+"/school/checker/ygClassCheck/?repage";
	}
	
	@RequiresPermissions("school:checker:ygClassCheck:edit")
	@RequestMapping(value = "delete")
	public String delete(YgClassCheck ygClassCheck, RedirectAttributes redirectAttributes) {
		ygClassCheckService.delete(ygClassCheck);
		addMessage(redirectAttributes, "删除巡查情况成功");
		return "redirect:"+Global.getAdminPath()+"/school/checker/ygClassCheck/?repage";
	}

}