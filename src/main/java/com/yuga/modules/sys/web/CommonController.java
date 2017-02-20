package com.yuga.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuga.common.utils.UploadUtils;

@Controller
@RequestMapping(value = "${adminPath}/common")
public class CommonController {
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			new UploadUtils().uploadImages(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
}
