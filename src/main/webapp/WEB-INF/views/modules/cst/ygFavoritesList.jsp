<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Favorites管理</title>
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
		<li class="active"><a href="${ctx}/cst/ygFavorites/">Favorites列表</a></li>
		<shiro:hasPermission name="cst:ygFavorites:edit"><li><a href="${ctx}/cst/ygFavorites/form">Favorites添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygFavorites" action="${ctx}/cst/ygFavorites/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Job ID：</label>
				<form:input path="jobId" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>Consultant ID：</label>
				<form:input path="consultantId" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>Create Time：</label>
				<form:input path="createTime" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>Belond user.：</label>
				<sys:treeselect id="user" name="user.id" value="${ygFavorites.user.id}" labelName="user.name" labelValue="${ygFavorites.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Job ID</th>
				<th>Consultant ID</th>
				<th>Create Time</th>
				<th>Belond user.</th>
				<shiro:hasPermission name="cst:ygFavorites:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygFavorites">
			<tr>
				<td><a href="${ctx}/cst/ygFavorites/form?id=${ygFavorites.id}">
					${ygFavorites.jobId}
				</a></td>
				<td>
					${ygFavorites.consultantId}
				</td>
				<td>
					${ygFavorites.createTime}
				</td>
				<td>
					${ygFavorites.user.name}
				</td>
				<shiro:hasPermission name="cst:ygFavorites:edit"><td>
    				<a href="${ctx}/cst/ygFavorites/form?id=${ygFavorites.id}">修改</a>
					<a href="${ctx}/cst/ygFavorites/delete?id=${ygFavorites.id}" onclick="return confirmx('确认要删除该Favorites吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>