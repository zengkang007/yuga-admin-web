<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信记录管理</title>
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
		<li class="active"><a href="${ctx}/sms/smsRecord/">短信记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="smsRecord" action="${ctx}/sms/smsRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业编号：</label>
				<form:input path="brandid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>发送状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>企业编号</th>
				<th>内容</th>
				<th>接收人</th>
				<th>耗时时间</th>
				<th>发送时间</th>
				<th>发送状态</th>
				<shiro:hasPermission name="sms:smsRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smsRecord">
			<tr>
				<td><a href="${ctx}/sms/smsRecord/form?id=${smsRecord.id}">
					${smsRecord.brandid}
				</a></td>
				<td>
					${smsRecord.content}
				</td>
				<td>
					${smsRecord.phonenum}
				</td>
				<td>
					${smsRecord.elapsetime}
				</td>
				<td>
					<fmt:formatDate value="${smsRecord.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${smsRecord.status}
				</td>
				<shiro:hasPermission name="sms:smsRecord:edit"><td>
    				<a href="${ctx}/sms/smsRecord/form?id=${smsRecord.id}">修改</a>
					<a href="${ctx}/sms/smsRecord/delete?id=${smsRecord.id}" onclick="return confirmx('确认要删除该短信记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>