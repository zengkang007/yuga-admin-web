<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师教学情况管理</title>
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
		<li class="active"><a href="${ctx}/school/base/ygTeacherStatus/">教师教学情况列表</a></li>
		<shiro:hasPermission name="school:base:ygTeacherStatus:edit"><li><a href="${ctx}/school/base/ygTeacherStatus/form">教师教学情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygTeacherStatus" action="${ctx}/school/base/ygTeacherStatus/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>对应等级：</label>
				<form:input path="level" htmlEscape="false" maxlength="10" class="input-medium"/>
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
				<th>对应内容</th>
				<shiro:hasPermission name="school:base:ygTeacherStatus:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygTeacherStatus">
			<tr>
				<td><a href="${ctx}/school/base/ygTeacherStatus/form?id=${ygTeacherStatus.id}">
					${ygTeacherStatus.level}
				</a></td>
				<td>
					${ygTeacherStatus.content}
				</td>
				<shiro:hasPermission name="school:base:ygTeacherStatus:edit"><td>
    				<a href="${ctx}/school/base/ygTeacherStatus/form?id=${ygTeacherStatus.id}">修改</a>
					<a href="${ctx}/school/base/ygTeacherStatus/delete?id=${ygTeacherStatus.id}" onclick="return confirmx('确认要删除该教师教学情况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>