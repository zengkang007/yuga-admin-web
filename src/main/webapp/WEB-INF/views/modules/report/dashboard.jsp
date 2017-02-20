<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- <link rel='stylesheet' href='/fullcalendar-2.4.0/lib/cupertino/jquery-ui.min.css' />
<link href='/fullcalendar-2.4.0/fullcalendar.css' rel='stylesheet' />
<link href='/fullcalendar-2.4.0/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='/fullcalendar-2.4.0/lib/moment.min.js'></script>
<script src='/fullcalendar-2.4.0/fullcalendar.min.js'></script>
<script src='/fullcalendar-2.4.0/lang-all.js'></script>
<script src='/module/report/dashboard.js'></script> -->
<html>
<head>
	<title>任务看板</title>
	<meta name="decorator" content="default"/>
	<sys:import includes="fullcalendar"></sys:import>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/ksmTask/">任务列表</a></li>
		<shiro:hasPermission name="report:ksmTask:edit"><li><a href="${ctx}/report/ksmTask/form">任务添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<div class="alert alert-danger" role="alert">
      <strong>你有 2条日报没填写 </strong> <a href="#" class="alert-link">填写日报</a>
    </div>
    <div id='top'>
		Language:
		<select id='lang-selector'></select>
	</div>
	<div id='calendar'></div>	
</body>
</html>