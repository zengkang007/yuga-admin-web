<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
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
		<li class="active"><a href="${ctx}/school/base/ygStudentUser/">学生信息列表</a></li>
		<shiro:hasPermission name="school:base:ygStudentUser:edit"><li><a href="${ctx}/school/base/ygStudentUser/form">学生信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygStudentUser" action="${ctx}/school/base/ygStudentUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学号：</label>
				<form:input path="schoolNo" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>监护人姓名：</label>
				<form:input path="contactUserName" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>监护人电话：</label>
				<form:input path="contactTelNo" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>家庭地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>所属年级：</label>
				<form:select path="gradeNo" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>学号</th>
				<th>监护人姓名</th>
				<th>监护人电话</th>
				<th>家庭地址</th>
				<th>所属年级</th>
				<shiro:hasPermission name="school:base:ygStudentUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygStudentUser">
			<tr>
				<td><a href="${ctx}/school/base/ygStudentUser/form?id=${ygStudentUser.id}">
					${ygStudentUser.schoolNo}
				</a></td>
				<td>
					${ygStudentUser.contactUserName}
				</td>
				<td>
					${ygStudentUser.contactTelNo}
				</td>
				<td>
					${ygStudentUser.address}
				</td>
				<td>
					${fns:getGradeLabel(ygStudentUser.gradeNo)}
				</td>
				<shiro:hasPermission name="school:base:ygStudentUser:edit"><td>
    				<a href="${ctx}/school/base/ygStudentUser/form?id=${ygStudentUser.id}">修改</a>
					<a href="${ctx}/school/base/ygStudentUser/delete?id=${ygStudentUser.id}" onclick="return confirmx('确认要删除该学生信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>