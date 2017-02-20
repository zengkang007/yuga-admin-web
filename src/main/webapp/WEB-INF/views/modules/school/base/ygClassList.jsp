<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>班级信息管理</title>
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
		<li class="active"><a href="${ctx}/school/base/ygClass/">班级信息列表</a></li>
		<shiro:hasPermission name="school:base:ygClass:edit"><li><a href="${ctx}/school/base/ygClass/form">班级信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygClass" action="${ctx}/school/base/ygClass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建时间：</label>
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ygClass.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>班级名称：</label>
				<form:input path="className" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>班级地址：</label>
				<form:input path="classAddress" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>班级人数：</label>
				<form:input path="classCount" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>						
			<%-- <li><label>年级：</label>
				<form:input path="classGrade" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>班级名称</th>
				<th>班级地址</th>
				<th>班级人数</th>
				<th>创建时间</th>								
				<shiro:hasPermission name="school:base:ygClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygClass">
			<tr>
				<td><a href="${ctx}/school/base/ygClass/form?id=${ygClass.id}">
					${ygClass.className}
				</a></td>
				<td><a href="${ctx}/school/base/ygClass/form?id=${ygClass.id}">
					${ygClass.classAddress}
				</a></td>
				<td><a href="${ctx}/school/base/ygClass/form?id=${ygClass.id}">
					${ygClass.classCount}
				</a></td>								
				<td>
					<fmt:formatDate value="${ygClass.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="school:base:ygClass:edit"><td>
    				<a href="${ctx}/school/base/ygClass/form?id=${ygClass.id}">修改</a>
					<a href="${ctx}/school/base/ygClass/delete?id=${ygClass.id}" onclick="return confirmx('确认要删除该班级信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>