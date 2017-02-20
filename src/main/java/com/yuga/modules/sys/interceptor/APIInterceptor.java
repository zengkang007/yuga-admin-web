/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.sys.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 对外访问拦截器
 * 
 * @author ThinkGem
 * @version 2015-01-16
 */
public class APIInterceptor extends HandlerInterceptorAdapter {

	private static Logger log = LoggerFactory.getLogger(APIInterceptor.class);

	final public static String CACHE_INTERFACE_MGR = "CACHE_INTERFACE_MGR";
	final public static String CACHE_INTERFACE_KEY = "CACHE_INTERFACE_KEY";

	final private static int REST_API_TIMEOUT = -1;
	final private static int REST_API_NOT_CONFIG = 1;
	final private static int REST_API_OK = 2;
	final private static int REST_API_DISABLE = 0;
	
	private static Map<Integer, String> errorMap = new HashMap<Integer, String>();
	static{
		errorMap.put(REST_API_TIMEOUT, "You api is timeout, please contact manager.");
		errorMap.put(REST_API_NOT_CONFIG, "You api is not config, please contact manager.");
		errorMap.put(REST_API_DISABLE, "You api is disable, please contact manager.");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//设置返回
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Max-Age", "1800");//30 min	
		        
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
