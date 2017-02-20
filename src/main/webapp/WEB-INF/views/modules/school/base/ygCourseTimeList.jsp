<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课时维护管理</title>
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
		<li class="active"><a href="${ctx}/school/base/ygCourseTime/">课时维护列表</a></li>
		<shiro:hasPermission name="school:base:ygCourseTime:edit"><li><a href="${ctx}/school/base/ygCourseTime/form">课时维护添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygCourseTime" action="${ctx}/school/base/ygCourseTime/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课程章节：</label>
				<form:input path="courseName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>上课时间：</label>
				<form:input path="timeRange" htmlEscape="false" maxlength="120" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程章节</th>
				<th>上课时间</th>
				<shiro:hasPermission name="school:base:ygCourseTime:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygCourseTime">
			<tr>
				<td><a href="${ctx}/school/base/ygCourseTime/form?id=${ygCourseTime.id}">
					${ygCourseTime.courseName}
				</a></td>
				<td>
					${ygCourseTime.timeRange}
				</td>
			
				<shiro:hasPermission name="school:base:ygCourseTime:edit"><td>
    				<a href="${ctx}/school/base/ygCourseTime/form?id=${ygCourseTime.id}">修改</a>
					<a href="${ctx}/school/base/ygCourseTime/delete?id=${ygCourseTime.id}" onclick="return confirmx('确认要删除该课时维护吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>