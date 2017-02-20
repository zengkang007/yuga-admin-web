<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="decorator" content="blank"/>

    
<title>欢迎使用韩家墩小学数字化校园系统！</title>
<link href="${ctxStatic}/hjd/css/sc.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
<script language=Javascript> 
  function time(){
    //  获得显示时间的div
    t_div = document.getElementById('showtime');
   var now=new Date()
    //替换div内容 
   t_div.innerHTML = "现在是"+now.getFullYear()
    +"年"+(now.getMonth()+1)+"月"+now.getDate()
    +"日"+now.getHours()+"时"+now.getMinutes()
    +"分"+now.getSeconds()+"秒";
    //等待一秒钟后调用time方法，由于settimeout在time方法内，所以可以无限调用
   setTimeout(time,1000);
  } 
</script>
</head>
<body  onload="time()">


<div id="login_all">
  <div id="login_top">
    <div style="float:left; text-indent:0.5em;color:#0b0b0b;">欢迎使用韩家墩小学数字化校园系统！</div>
    
    <div   style="float:center;" id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
            <button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
	</div>
		
    <div id="showtime" style="float:right; margin-right:8px;color:#0b0b0b;">今天是：2016年1月23日  星期三</div>
  </div>
  
  	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
  	<input type="hidden" id="debug" name="debug" value="true"/>
  <div id="login_tep">
    <div id="bt">
		 <p   style="margin: auto; font-size:35px; font-weight:bolder; font-family:Arial, Helvetica, sans-serif;color:#0b0b0b;height:60px; width:560px ;">韩家墩小学数字化校园系统</p>
	</div>
    <div class="input_test">
      <table width="480" border="0" id="tab_res">
        <tr>
          <td width="60">用户名：</td>
		  
          <td width="214" colspan="2"><input  tabindex="1" class="input_out" id="username" name="username" type="text"  value="${username}"/></td>
          <td><span></span></td>
        </tr>

        <tr>
          <td>密&nbsp;码：</td>
          <td colspan="2"><input tabindex="2" class="input_out"  type="password" id="password" name="password"  /></td>
        </tr>
        
        <c:if test="${isValidateCodeLogin}">
        <tr>         
          <td>验证码：</td>
          <td colspan="3">
        <div class="validateCode">
			<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
		</div>
		</td>
        </tr>  
        </c:if>
        
        <tr>
          <td>记住我：</td>
          <td>
          	<label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> </label>
          </td>
          <td style="color:#666666;"><span>公共场所慎用</span></td>
        </tr>
        <tr>
        
          <td>&nbsp;</td>
          <td colspan="3" id="btn_zz">
		  <input  name="" type="submit"  class="btn btn-large btn-primary"  tabindex="4" value="登陆" /></a>
          </td>
        </tr>
      </table>
    </div>
  </div>
  	</form>
  
  <div id="footerwarp">@copyright 2016  韩家墩小学数字化校园系统版权所有，建议使用IE7及以上浏览器，1024*768分辨率！</div>
</div>
</body>
</html>
