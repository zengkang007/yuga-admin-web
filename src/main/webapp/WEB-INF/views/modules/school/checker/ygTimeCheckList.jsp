<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡检时间主表管理</title>
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
		<li class="active"><a href="${ctx}/school/checker/ygTimeCheck/">巡检时间主表列表</a></li>
		<shiro:hasPermission name="school:checker:ygTimeCheck:edit"><li><a href="${ctx}/school/checker/ygTimeCheck/form">巡检时间主表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygTimeCheck" action="${ctx}/school/checker/ygTimeCheck/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ygTimeCheck.startTime}" pattern="HH:mm"/>"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ygTimeCheck.endTime}" pattern="HH:mm"/>"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="school:checker:ygTimeCheck:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygTimeCheck">
			<tr>
				<td><a href="${ctx}/school/checker/ygTimeCheck/form?id=${ygTimeCheck.id}">
					${ygTimeCheck.name}
				</a></td>
				<td>
					${ygTimeCheck.startTime}
				</td>
				<td>
					${ygTimeCheck.endTime}
				</td>
				<shiro:hasPermission name="school:checker:ygTimeCheck:edit"><td>
    				<a href="${ctx}/school/checker/ygTimeCheck/form?id=${ygTimeCheck.id}">修改</a>
					<a href="${ctx}/school/checker/ygTimeCheck/delete?id=${ygTimeCheck.id}" onclick="return confirmx('确认要删除该巡检时间主表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>