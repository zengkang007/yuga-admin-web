<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务管理</title>
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
		<li class="active"><a href="${ctx}/report/ksmTask/">任务列表</a></li>
		<shiro:hasPermission name="report:ksmTask:edit"><li><a href="${ctx}/report/ksmTask/form">任务添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ksmTask" action="${ctx}/report/ksmTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ksmTask.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ksmTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>任务名称：</label>
				<form:input path="taskName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>任务时长：</label>
				<form:input path="taskLast" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ksm_report_work_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所有者：</label>
				<form:input path="owner" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<div class="alert alert-danger" role="alert">
      <strong>你有 2条日报没填写 </strong> <a href="#" class="alert-link">填写日报</a>
    </div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>任务名称</th>
				<th>任务时长</th>
				<th>状态</th>
				<th>所有者</th>
				<th>备注</th>
				<th>创建者</th>
				<th>创建时间</th>
				<shiro:hasPermission name="report:ksmTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ksmTask">
			<tr>
				<td><a href="${ctx}/report/ksmTask/form?id=${ksmTask.id}">
					<fmt:formatDate value="${ksmTask.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					<fmt:formatDate value="${ksmTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ksmTask.taskName}
				</td>
				<td>
					${ksmTask.taskLast}
				</td>
				<td>
					${fns:getDictLabel(ksmTask.status, 'ksm_report_work_status', '')}
				</td>
				<td>
					${ksmTask.owner}
				</td>
				<td>
					${ksmTask.remarks}
				</td>
				<td>
					${ksmTask.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${ksmTask.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="report:ksmTask:edit"><td>
    				<a href="${ctx}/report/ksmTask/form?id=${ksmTask.id}">修改</a>
					<a href="${ctx}/report/ksmTask/delete?id=${ksmTask.id}" onclick="return confirmx('确认要删除该任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>