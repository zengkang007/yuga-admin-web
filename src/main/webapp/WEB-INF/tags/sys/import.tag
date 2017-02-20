<%@tag import="com.yuga.common.config.ResGlobal"%>
<%@tag import="javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar"%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<%@ attribute name="includes" type="java.lang.String" required="true"%>
<%
	//DEFINE JS TO IMPORT!!
	//1.Compress and merge js,css resouces.
	//2.check the duplicate resouce
	
	//Default lib
	
	if(!"".equals(includes)){		
		String[] resList = includes.split(",");
		for(String keyUrl : resList) {
			if("".equals(keyUrl)) {throw new Exception(keyUrl+"Not found!");}
			if(keyUrl.endsWith("Css")) {
				System.out.println("css keyUrl ====> " + ResGlobal.getConfig(keyUrl));
				String[] cssResList = ResGlobal.getConfig(keyUrl).split(",");
				for(String css : cssResList) {
					System.out.println("css:" + css);
			%>			
			<link rel="stylesheet" href="${ctxStatic}/<%=css%>" />
			<% 
				}
			} else {
				String[] jsResList = null;
				try{
					jsResList = ResGlobal.getConfig(keyUrl).split(",");
				}catch(Exception e) {
					System.err.print("找不到 keyUrl: " + keyUrl);
					e.printStackTrace();
				}
				for(String js : jsResList) {
			%>
			<script src="${ctxStatic}/<%=js%>" charset="UTF-8" ></script>
			<% 	
				}
			}
		}
	}
%>
