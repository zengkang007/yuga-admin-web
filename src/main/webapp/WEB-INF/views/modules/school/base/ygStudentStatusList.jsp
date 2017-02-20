<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生学习情况管理</title>
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
		<li class="active"><a href="${ctx}/school/base/ygStudentStatus/">学生学习情况列表</a></li>
		<shiro:hasPermission name="school:base:ygStudentStatus:edit"><li><a href="${ctx}/school/base/ygStudentStatus/form">学生学习情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygStudentStatus" action="${ctx}/school/base/ygStudentStatus/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>对应等级：</label>
				<form:input path="level" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>等级说明：</label>
				<form:input path="content" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>对应等级</th>
				<th>等级说明</th>
				<shiro:hasPermission name="school:base:ygStudentStatus:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygStudentStatus">
			<tr>
				<td><a href="${ctx}/school/base/ygStudentStatus/form?id=${ygStudentStatus.id}">
					${ygStudentStatus.level}
				</a></td>
				<td>
					${ygStudentStatus.content}
				</td>
				<shiro:hasPermission name="school:base:ygStudentStatus:edit"><td>
    				<a href="${ctx}/school/base/ygStudentStatus/form?id=${ygStudentStatus.id}">修改</a>
					<a href="${ctx}/school/base/ygStudentStatus/delete?id=${ygStudentStatus.id}" onclick="return confirmx('确认要删除该学生学习情况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>