<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师信息管理</title>
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
		<li class="active"><a href="${ctx}/school/base/ygTeacherUser/">教师信息列表</a></li>
		<shiro:hasPermission name="school:base:ygTeacherUser:edit"><li><a href="${ctx}/school/base/ygTeacherUser/form">教师信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygTeacherUser" action="${ctx}/school/base/ygTeacherUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<sys:treeselect id="user" name="user.id" value="${ygTeacherUser.user.id}" labelName="user.name" labelValue="${ygTeacherUser.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>所属班级：</label>
				<form:input path="classId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>所属班级</th>
				<shiro:hasPermission name="school:base:ygTeacherUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygTeacherUser">
			<tr>
				<td><a href="${ctx}/school/base/ygTeacherUser/form?id=${ygTeacherUser.id}">
					${ygTeacherUser.user.name}
				</a></td>
				<td>
					${ygTeacherUser.classId}
				</td>
				<shiro:hasPermission name="school:base:ygTeacherUser:edit"><td>
    				<a href="${ctx}/school/base/ygTeacherUser/form?id=${ygTeacherUser.id}">修改</a>
					<a href="${ctx}/school/base/ygTeacherUser/delete?id=${ygTeacherUser.id}" onclick="return confirmx('确认要删除该教师信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>