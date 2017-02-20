<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Notification</title>
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
		<li class="active"><a href="${ctx}/cst/ygNotification/">Notification List</a></li>
		<shiro:hasPermission name="cst:ygNotification:edit"><li><a href="${ctx}/cst/ygNotification/form">Notification View</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygNotification" action="${ctx}/cst/ygNotification/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Title：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>Content：</label>
				<form:input path="content" htmlEscape="false" maxlength="1024" class="input-medium"/>
			</li>
			<li><label>Send time：</label>
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ygNotification.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>Priority：</label>
				<form:select path="priority" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cst_priority')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Query"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Title</th>
				<th>Content</th>
				<th>Send time</th>
				<th>Priority</th>
				<shiro:hasPermission name="cst:ygNotification:edit"><th>Operate</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygNotification">
			<tr>
				<td><a href="${ctx}/cst/ygNotification/form?id=${ygNotification.id}">
					${ygNotification.title}
				</a></td>
				<td>
					${ygNotification.content}
				</td>
				<td>
					<fmt:formatDate value="${ygNotification.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(ygNotification.priority, 'yg_cst_priority', '')}
				</td>
				<shiro:hasPermission name="cst:ygNotification:edit"><td>
    				<a href="${ctx}/cst/ygNotification/form?id=${ygNotification.id}">View</a>
					<%--<a href="${ctx}/cst/ygNotification/delete?id=${ygNotification.id}" onclick="return confirmx('确认要删除该Notification吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>