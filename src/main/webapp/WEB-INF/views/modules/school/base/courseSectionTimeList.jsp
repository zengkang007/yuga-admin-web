<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节次时间管理</title>
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
		<li class="active"><a href="${ctx}/school/base/courseSectionTime/">节次时间列表</a></li>
		<shiro:hasPermission name="school:base:courseSectionTime:edit"><li><a href="${ctx}/school/base/courseSectionTime/form">节次时间添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseSectionTime" action="${ctx}/school/base/courseSectionTime/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>节次：</label>
				<form:select path="sectionId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_course_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${courseSectionTime.startTime}"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${courseSectionTime.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>节次</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="school:base:courseSectionTime:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseSectionTime">
			<tr>
				<td><a href="${ctx}/school/base/courseSectionTime/form?id=${courseSectionTime.id}">
					${fns:getDictLabel(courseSectionTime.sectionId, 'yg_course_no', '')}
				</a></td>
				<td>
					${courseSectionTime.startTime}
				</td>
				<td>
					${courseSectionTime.endTime}
				</td>
				<shiro:hasPermission name="school:base:courseSectionTime:edit"><td>
    				<a href="${ctx}/school/base/courseSectionTime/form?id=${courseSectionTime.id}">修改</a>
					<a href="${ctx}/school/base/courseSectionTime/delete?id=${courseSectionTime.id}" onclick="return confirmx('确认要删除该节次时间吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>