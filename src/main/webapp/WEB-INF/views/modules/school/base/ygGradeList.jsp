<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>年级级别管理</title>
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
		<li class="active"><a href="${ctx}/school/base/ygGrade/">年级级别列表</a></li>
		<shiro:hasPermission name="school:base:ygGrade:edit"><li><a href="${ctx}/school/base/ygGrade/form">年级级别添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygGrade" action="${ctx}/school/base/ygGrade/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年级名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年级名称</th>
				<shiro:hasPermission name="school:base:ygGrade:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygGrade">
			<tr>
				<td><a href="${ctx}/school/base/ygGrade/form?id=${ygGrade.id}">
					${ygGrade.name}
				</a></td>
				<shiro:hasPermission name="school:base:ygGrade:edit"><td>
    				<a href="${ctx}/school/base/ygGrade/form?id=${ygGrade.id}">修改</a>
					<a href="${ctx}/school/base/ygGrade/delete?id=${ygGrade.id}" onclick="return confirmx('确认要删除该年级级别吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>