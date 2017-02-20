<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>软路由管理</title>
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
		<li class="active"><a href="${ctx}/route/">品牌列表</a></li>
		<shiro:hasPermission name="route:companyRoute:edit"><li><a href="${ctx}/route/form">品牌添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="companyRoute" action="${ctx}/route/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>显示子标题：</label>
				<form:input path="subtitle" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>			
			<li><label>版本号：</label>
				<form:input path="version" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>转发IP：</label>
				<form:input path="ipaddr" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>显示子标题</th>
				<th>企业编号</th>
				<th>是否启用</th>
				<th>版本号</th>
				<th>转发IP</th>
				<shiro:hasPermission name="route:companyRoute:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="companyRoute">
			<tr>
				<td><a href="${ctx}/route/form?id=${companyRoute.id}">
					${companyRoute.subtitle}
				</a></td>
				<td>
					${companyRoute.brandId}					
				</td>
				<td>
					${fns:getDictLabel(companyRoute.enable, 'yes_no', '')}
				</td>
				<td>
					${companyRoute.version}
				</td>
				<td>
					${companyRoute.ipaddr}
				</td>
				<shiro:hasPermission name="route:companyRoute:edit"><td>
    				<a href="${ctx}/route/form?id=${companyRoute.id}">修改</a>
					<a href="${ctx}/route/delete?id=${companyRoute.id}" onclick="return confirmx('确认要删除该软路由吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>