<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业短信配置管理</title>
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
		<li class="active"><a href="${ctx}/sms/companySms/">企业短信配置列表</a></li>
		<shiro:hasPermission name="sms:companySms:edit"><li><a href="${ctx}/sms/companySms/form">企业短信配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="companySms" action="${ctx}/sms/companySms/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>接口名称：</label>
				<form:input path="companyinf" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>企业编号：</label>
				<form:input path="brandid" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>accessKey：</label>
				<form:input path="accesskey" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>			
				<th>接口名称</th>
				<th>企业编号</th>
				<th>剩余条数</th>
				<th>accessKey</th>
				<th>是否启用</th>
				<shiro:hasPermission name="sms:companySms:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="companySms">
			<tr>			
				<td>
					${companySms.companyinf}
				</td>
				<td><a href="${ctx}/sms/companySms/form?id=${companySms.id}">
					${companySms.brandid}
				</a></td>
				<td>
					${companySms.remainnum}
				</td>
				<td>
					${companySms.accesskey}
				</td>
				<td>
					${fns:getDictLabel(companySms.enable, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="sms:companySms:edit"><td>
    				<a href="${ctx}/sms/companySms/form?id=${companySms.id}">修改</a>
					<a href="${ctx}/sms/companySms/delete?id=${companySms.id}" onclick="return confirmx('确认要删除该企业短信配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>