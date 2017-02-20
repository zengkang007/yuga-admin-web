<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

<title>韩家墩小学数字化校园系统</title>
<link href="${ctxStatic}/hjd/css/sc.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/hjd/css/slide-2.css" rel="stylesheet" type="text/css"  />
<script type="text/javascript" src="${ctxStatic}/hjd/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/hjd/js/slide.js"></script>

	<style type="text/css">
      html,body,table{background-color:#3E75C0;width:100%;text-align:center;}
      body{background:url(${ctxStatic}/hjd/img/b3.jpg) ;}
    </style>



<!--皮肤选择
<script type="text/javascript">
	//var cssPath = "${ctxStatic}/hjd/css/style_switch";
	//var styleID=3;
	//document.write('<link rel="stylesheet" type="text\/css" id="cssLink" href="'+cssPath+styleID+'.css" \/>');
	//var cssLink=document.getElementById("cssLink");
	//function styleChange(n) {
	//	if(n==3){
	//	document.getElementById("select").style.background="";
	//	}
	//	else{
	//	document.getElementById("select").style.background="";
	//	}
	//	cssLink.href = cssPath+n+".css";			
	//}
	

</script>
-->

</head>


<body>
<div id="index_all">
  <div id="index_top"><span style="float:left; text-indent:0.5em;">欢迎${fns:getUser().name},使用韩家墩小学数字化校园系统！</span>
    <div id="index_right">
      <!--<div id="pic1"><img src="${ctxStatic}/hjd/img/a3.png" width="27" height="26" /><span title="点击换肤" onclick="mask1.style.visibility='visible';massage_box1.style.visibility='visible'" style="cursor:hand"><a href="#">换肤</a></span>
        <div id="massage_box1">
          <div class="massage1">
            <div class="header1" onmousedown=MDown(massage_box1)>
              <div style=" width:150px; height:26px; position:absolute">选择皮肤</div>
              <span onclick="massage_box1.style.visibility='hidden'; mask1.style.visibility='hidden'" style="float:right; display:inline; cursor:pointer; background:url(${ctxStatic}/hjd/img/close1.png) no-repeat; width:34px; height:34px; margin-top:4px;"></span></div>
            <div id="select" class="select"> <a href="javascript:styleChange(1);" class="m1"><img src="${ctxStatic}/hjd/img/main_03.jpg" width="62" height="62" /></a><a href="javascript:styleChange(2);" class="m2"><img src="${ctxStatic}/hjd/img/main_05.jpg" width="62" height="62" /></a><a href="javascript:styleChange(3);" class="m3"><img src="${ctxStatic}/hjd/img/main_07.jpg" width="62" height="62" /></a><a href="javascript:styleChange(4);" class="m4"><img src="${ctxStatic}/hjd/img/main_12.jpg" width="62" height="62" /></a><a href="javascript:styleChange(5);" class="m5"><img src="${ctxStatic}/hjd/img/main_13.jpg" width="62" height="62" /></a><a href="javascript:styleChange(6);" class="m6"><img src="${ctxStatic}/hjd/img/main_14.jpg" width="62" height="62" /></a><a href="javascript:styleChange(7);" class="m7"><img src="${ctxStatic}/hjd/img/main_18.jpg" width="62" height="62" /></a><a href="javascript:styleChange(8);" class="m8"><img src="${ctxStatic}/hjd/img/main_19.jpg" width="62" height="62" /></a><a href="javascript:styleChange(9);" class="m9"><img src="${ctxStatic}/hjd/img/main_20.jpg" width="62" height="62" /></a></div>
          </div>
        </div>
        <div id="mask1"></div>
      </div>-->
      <div id="pic2"> <a href="${ctx}"><img src="${ctxStatic}/hjd/img/a2.png" width="30" height="30" />返回</a></div>
      <div id="pic3"> <a href="${ctx}/logout"><img src="${ctxStatic}/hjd/img/a3.png" width="30" height="30" />退出</a></div>
    </div>
  </div>
  <!--top结束-->
  <div id="index_title">
    
    <!-- <div id="man">
      <table width="137" border="0">
        <tr>
          <td width="68" height="68" style=" padding:0px;">
          <div id="man_bor">
          <img onclick="mask.style.visibility='visible';massage_box.style.visibility='visible'" title="点击查看个人详细信息" src="${ctxStatic}/hjd/img/man1.png" width="60" height="60" style="cursor:pointer;" />
          </div>
          </td>
          <td><span title="点击查看个人详细信息" onclick="mask.style.visibility='visible';massage_box.style.visibility='visible'" style="cursor:hand"><a href="#">${fns:getUser().name}</a></span><br />
            <div id="massage_box">
              <div class="massage">
                <div class="header" onmousedown=MDown(massage_box)>
                  <div style=" width:150px; height:26px; position:absolute; text-align:center;">个人信息详情</div>
                  <span onclick="massage_box.style.visibility='hidden'; mask.style.visibility='hidden'" style="float:right; display:inline; cursor:pointer; background:url(${ctxStatic}/hjd/img/close1.png) no-repeat; width:34px; height:34px; margin-top:4px; margin-right:3px;"></span></div>
                <ul style="margin-right:25">
                  <li>登录帐号：${fns:getUser().loginName}</li>
                  <li>姓名：${fns:getUser().name}</li>
                  <li>电话：${fns:getUser().phone}</li>
                  <li>手机：${fns:getUser().mobile}</li>
                  <li>姓名：${fns:getUser().email}</li>
                  <li>部门：${fns:getUser().office}</li>

                </ul>
               <a onclick="Javascript:window.open('${ctx}/sys/user/info',700,570); massage_box.style.visibility='hidden'" style="float:right; margin-right:10px; cursor:pointer;">编辑</a> </div>
                 </div>

            <div id="mask"></div>
            <a style="color:#FFFFFF;" href="${ctx}/logout">退出</a></td>
        </tr>
      </table>
    </div>-->
    <div id="tit_pic" style="margin-top: 0;" >
	 <p   style="margin-top: 0; font-size:35px; font-family:Arial, Helvetica, sans-serif;color:#FFFFFF;height:60px; width:560px ;">韩家墩小学数字化校园系统</p>

	</div>
	
	<!--  <div id="tianqi">
	<a href="${ctx}">
       <img src="${ctxStatic}/hjd/img/tq.png" width="65" height="60" />
        <p style=" font-size:15px; font-family:Arial, Helvetica, sans-serif;color:#FFFFFF;height:15px;">首页</p>
      </a>
	</div>-->
	

  </div>
  <!--标题结束   -->

  
  
  <div id="main">
		
	<div id="right">
		
	<div class="ca1_slide">
      <div id="slider-wrapper">
        <div id="slider-bg">
          <div id="slider-photos">
            <div id="slides">
              <div class="slides_container">
                <div class="slide">
                  <div id="icon_all">



							
                    <div id="p1">
                    
                    <c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
					<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
                    <div id="s1y1" >
                       <p   style=" font-size:18px; font-family:Arial, Helvetica, sans-serif;color:#FFFFFF;height:25px;line-height:25px;">${menu.name}</p>
                      <div id="y1">
                      <a  class="menu" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}"  data-id="${menu.id}">
                      
                      <c:if test="${menu.name eq '系统设置'}">
                      <img src="${ctxStatic}/hjd/img/MGC.png" width="100" height="100" />
                      </c:if>
                      <c:if test="${menu.name eq '我的面板'}">
                      <img src="${ctxStatic}/hjd/img/MDD.png" width="100" height="100" />
                      </c:if>
                      <c:if test="${menu.name eq '内容管理'}">
                      <img src="${ctxStatic}/hjd/img/MXF.png" width="100" height="100" />
                      </c:if>
                      
                      </a>  
                      </div>
                    </div>
							</c:if>
						</c:forEach>
					  	
					  </div>							

                    <div id="p2">
                     
                    <c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
					<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
					<div id="menu-${menu.id}"  class="accordion">
                       <p   style=" font-size:25px; font-family:Arial, Helvetica, sans-serif;color:#FFFFFF;height:25px;line-height:25px;">${menu.name}</p>

					<c:forEach items="${menuList}" var="menu2">
					<c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">
					
					<div id="s1y1" >

                       <p   style=" font-size:18px; font-family:Arial, Helvetica, sans-serif;color:#FFFFFF;height:25px;line-height:25px;">${menu2.name}</p>
                       <c:forEach items="${menuList}" var="menu3"><c:if test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
                      <div id="y1"  class="y1">
                      
                  <a  href="${fn:indexOf(menu3.href, '://') eq -1 ? ctx : ''}${not empty menu3.href ? menu3.href : '/404'}"  target="${not empty menu3.target ? menu3.target : '_blank'}">


                      <c:if test="${menu3.name eq '巡查管理'}">
                      <img src="${ctxStatic}/hjd/img/MYC.png" width="60" height="60" />
                      </c:if>

                      <c:if test="${menu3.name eq '班级管理'}">
                      <img src="${ctxStatic}/hjd/img/MXD.png" width="60" height="60" />
                      </c:if>

                      <c:if test="${menu3.name eq '课程管理'}">
                      <img src="${ctxStatic}/hjd/img/MXB.png" width="60" height="60" />
                      </c:if>
                      
                      <c:if test="${menu3.name eq '课时维护'}">
                      <img src="${ctxStatic}/hjd/img/MXF.png" width="60" height="60" />
                      </c:if>
                      
                      <c:if test="${menu3.name eq '教师信息维护'}">
                      <img src="${ctxStatic}/hjd/img/MXH.png" width="60" height="60" />
                      </c:if>                      
                      <c:if test="${menu3.name eq '学生信息维护'}">
                      <img src="${ctxStatic}/hjd/img/MXI.png" width="60" height="60" />
                      </c:if>                      
                      <c:if test="${menu3.name eq '基本课表'}">
                      <img src="${ctxStatic}/hjd/img/MXE.png" width="60" height="60" />
                      </c:if>                      
                      <c:if test="${menu3.name eq '修改密码'}">
                      <img src="${ctxStatic}/hjd/img/MGA.png" width="60" height="60" />
                      </c:if> 



                      <c:if test="${menu3.name eq '用户管理'}">
                      <img src="${ctxStatic}/hjd/img/MJA.png" width="60" height="60" />
                      </c:if>

                      <c:if test="${menu3.name eq '机构管理'}">
                      <img src="${ctxStatic}/hjd/img/MJB.png" width="60" height="60" />
                      </c:if>

                      <c:if test="${menu3.name eq '区域管理'}">
                      <img src="${ctxStatic}/hjd/img/MJC.png" width="60" height="60" />
                      </c:if>
                      
                      <c:if test="${menu3.name eq '菜单管理'}">
                      <img src="${ctxStatic}/hjd/img/MJD.png" width="60" height="60" />
                      </c:if>
                      
                      <c:if test="${menu3.name eq '字典管理'}">
                      <img src="${ctxStatic}/hjd/img/MJF.png" width="60" height="60" />
                      </c:if>                      
                      <c:if test="${menu3.name eq '角色管理'}">
                      <img src="${ctxStatic}/hjd/img/MJG.png" width="60" height="60" />
                      </c:if>                      
                      <c:if test="${menu3.name eq '日志查询'}">
                      <img src="${ctxStatic}/hjd/img/MJD.png" width="60" height="60" />
                      </c:if>                      
                      <c:if test="${menu3.name eq '连接池监视'}">
                      <img src="${ctxStatic}/hjd/img/MJA.png" width="60" height="60" />
                      </c:if> 
                                                                                       

                        <br>${menu3.name}</a> 
                      </div>   
                         </c:if></c:forEach>                                                                                              
                       
                    </div>

					</c:if>
					</c:forEach>
					
					</div>
					
							</c:if>
						</c:forEach>
				
                 
                </div>


                  </div>
                </div>

              </div>
          </div>
        </div>
      </div>
    </div>
				
				<!--  <iframe id="mainFrame" name="mainFrame" src="" style="" scrolling="yes" frameborder="no" width="980px" height="600px" ></iframe>-->
	
	</div>

</div>

</div>


	 <script type="text/javascript">
		$(document).ready(function() {
			var menuId = "";
			$("#right .accordion").hide();
			
			// 绑定菜单单击事件
			$("#p1  a.menu").click(function(){

				menuId = "#menu-" + $(this).attr("data-id");
				$("#p1").hide();
				$(menuId).show();
			});

			// 绑定菜单单击事件   
			//$("#p2 .y1").click(function(){

			//	$("#slider-wrapper").hide();
				//$("#mainFrame").css("height","500px");
			//});	


			// 绑定返回事件   
			//$("#pic2").click(function(){

			//	if(!$("#slider-wrapper").is(':hidden')){//如果详细页面不隐藏,则返回二级菜单页
			//		$("#slider-wrapper").hide();                     
			//		$("#p1").show();

            //     }

				
			//	if(!$("#mainFrame").is(':hidden')){//如果详细页面不隐藏,则返回二级菜单页
			//	    $("#mainFrame").hide();
			//		$("#slider-wrapper").show();
            //     }

			//});				
			
		});

	</script> 
  <div id="footerwarp">@copyright 2016  韩家墩小学数字化校园系统版权所有，  建议使用IE7及以上浏览器，1024*768分辨率！</div>
</div>
</body>
</html>
