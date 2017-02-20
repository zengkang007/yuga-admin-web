package com.yuga.modules.school.web.checker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuga.modules.school.entity.base.BaseCourse;
import com.yuga.modules.school.entity.checker.ClassCheckDTO;
import com.yuga.modules.school.service.base.BaseCourseService;
import com.yuga.modules.school.service.checker.YgClassCheckService;

@Controller
@RequestMapping(value = "${adminPath}/api/rest")
public class RestAPIController {
	
	@Autowired
	private BaseCourseService baseCourseService;
	
	@Autowired
	private YgClassCheckService ygClassCheckService;
	
	@RequestMapping(value = "getTop10",method = RequestMethod.GET)
	@ResponseBody
	public List<ClassCheckDTO> findTop10List(){
		return ygClassCheckService.findTop10List();
	}
	
	@RequestMapping(value = "getCourseInfo")
	@ResponseBody
	public BaseCourse getCourseInfo(BaseCourse baseCourse) {
		BaseCourse ret = baseCourseService.getCourseInfo(baseCourse);
		return ret;
	}
}
