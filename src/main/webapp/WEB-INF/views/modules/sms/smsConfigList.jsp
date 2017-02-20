<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信通道管理</title>
	<meta name="decorator" content="default"/>
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
		<li class="active"><a href="${ctx}/sms/smsConfig/">短信通道列表</a></li>
		<shiro:hasPermission name="sms:smsConfig:edit"><li><a href="${ctx}/sms/smsConfig/form">短信通道添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="smsConfig" action="${ctx}/sms/smsConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>平台网关名：</label>
				<form:input path="gatewayname" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>企业id：</label>
				<form:input path="epid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>平均速度：</label>
				<form:input path="avgspeed" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>是否可用：</label>
				<form:select path="enable" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>接口编号</th>
				<th>平台网关名</th>
				<th>总剩余条数</th>
				<th>企业id</th>
				<th>平均发送速度</th>
				<th>是否可用</th>
				<shiro:hasPermission name="sms:smsConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smsConfig">
			<tr>
				<td>
					${smsConfig.id}
				</td>
				<td><a href="${ctx}/sms/smsConfig/form?id=${smsConfig.id}">
					${smsConfig.gatewayname}
				</a></td>
				<td>
					${smsConfig.totalnum}
				</td>
				<td>
					${smsConfig.epid}
				</td>
				<td>
					${smsConfig.avgspeed}
				</td>
				<td>
					${fns:getDictLabel(smsConfig.enable, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="sms:smsConfig:edit"><td>
    				<a href="${ctx}/sms/smsConfig/form?id=${smsConfig.id}">修改</a>
					<a href="${ctx}/sms/smsConfig/delete?id=${smsConfig.id}" onclick="return confirmx('确认要删除该短信通道吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>