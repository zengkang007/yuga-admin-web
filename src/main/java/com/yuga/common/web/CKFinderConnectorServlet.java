/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.common.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ckfinder.connector.ConnectorServlet;
import com.yuga.common.config.Global;
import com.yuga.common.utils.FileUtils;
import com.yuga.common.utils.UploadUtils;
import com.yuga.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.yuga.modules.sys.utils.UserUtils;

/**
 * CKFinderConnectorServlet
 * @author ThinkGem
 * @version 2014-06-25
 */
public class CKFinderConnectorServlet extends ConnectorServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(CKFinderConnectorServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		prepareGetResponse(request, response, false);
		super.doGet(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isReturn = isMobileUpload(request, response);
		if(!isReturn){
			prepareGetResponse(request, response, true);
			super.doPost(request, response);
			log.info("上传路径：" +  request.getRequestURI());
		} else {
			log.info("移动端上传.");
		}
	}
	
	/**
	 * 移动端上传
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean isMobileUpload(final HttpServletRequest request, final HttpServletResponse response) {
		String command = request.getParameter("command");
		if ("FILEUPLOAD".equals(command)){
			String userId = request.getParameter("userId");// 当前用户
			/*Member currentUser = UserUtils.getUserById(userId);
			if(currentUser == null) {
				return false;
			}*/
			try {
				new UploadUtils().uploadImages(request, response);
				log.info("上传成功。");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			};
		}
		return false;
	}
	
	private void prepareGetResponse(final HttpServletRequest request,
			final HttpServletResponse response, final boolean post) throws ServletException {
		Principal principal = (Principal) UserUtils.getPrincipal();
		if (principal == null){
			//return;
		}
		String command = request.getParameter("command");
		String type = request.getParameter("type");
		// 初始化时，如果startupPath文件夹不存在，则自动创建startupPath文件夹
		if ("Init".equals(command)){
			String startupPath = request.getParameter("startupPath");// 当前文件夹可指定为模块名
			if (startupPath!=null){
				String[] ss = startupPath.split(":");
				if (ss.length==2){
					String realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL
							+ principal + "/" + ss[0] + ss[1];
					FileUtils.createDirectory(FileUtils.path(realPath));
				}
			}
		}
		// 快捷上传，自动创建当前文件夹，并上传到该路径
		else if ("QuickUpload".equals(command) && type!=null){
			String userId = request.getParameter("userId");// 当前用户
			String currentFolder = request.getParameter("currentFolder");// 当前文件夹可指定为模块名
			/*Member currentUser = UserUtils.getUserById(userId);*/
			String fileUrl =  principal + "/" + type + (currentFolder != null ? currentFolder : "");
			if(principal == null) {
				/*if(currentUser != null ) {
					fileUrl = userId + "/" + type + (currentFolder != null ? currentFolder : "");
				} else {*/
					log.error("服务器上没有找到该用户.");
					try {
						PrintWriter writer = response.getWriter();
						Map<String,String> resultMap = new HashMap<String, String>();
						resultMap.put("result", "error");
						resultMap.put("code", "-2");
						resultMap.put("data", "user not exist");
						writer.println(resultMap);
						writer.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
//				}
			} 
			String realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL
					+ fileUrl;
			FileUtils.createDirectory(FileUtils.path(realPath));
			log.info("url:" + fileUrl);
			log.info("realPath:" + realPath);
		}
//		System.out.println("------------------------");
//		for (Object key : request.getParameterMap().keySet()){
//			System.out.println(key + ": " + request.getParameter(key.toString()));
//		}
	}
}
