<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/cee/ygCeeInfo/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
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
		<li class="active"><a href="${ctx}/cee/ygCeeInfo/">学生信息列表</a></li>
		<shiro:hasPermission name="cee:ygCeeInfo:edit"><li><a href="${ctx}/cee/ygCeeInfo/form">学生信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygCeeInfo" action="${ctx}/cee/ygCeeInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="pid" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>高考报名号：</label>
				<form:input path="ceeAppNo" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>专业：</label>
				<form:select path="major" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cee_major')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>提交人：</label>
				<form:input path="submitter" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<c:set var="user" scope="session" value="${fns:getUser().loginName}"/>
			<%-- <c:if test="${user == 'admin' }"> --%>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<%-- </c:if>  --%>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>身份证号</th>
				<th>高考报名号</th>
				<th>专业</th>
				<th>毕业高中</th>
				<th>高考分数</th>
				<th>提交人</th>
				<th>提交时间</th>
				<shiro:hasPermission name="cee:ygCeeInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygCeeInfo">
			<tr>
				<td><a href="${ctx}/cee/ygCeeInfo/form?id=${ygCeeInfo.ids}">
					${ygCeeInfo.name}
				</a></td>
				<td>
					${ygCeeInfo.pid}
				</td>
				<td>
					${ygCeeInfo.ceeAppNo}
				</td>
				<td>
					${fns:getDictLabel(ygCeeInfo.major, 'yg_cee_major', '')}
				</td>
				<td>
					${ygCeeInfo.highSchool}
				</td>
				<td>
					${ygCeeInfo.ceeMark}
				</td>
				<td>
					${ygCeeInfo.submitter}
				</td>
				<td>
					<fmt:formatDate value="${ygCeeInfo.submitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cee:ygCeeInfo:edit"><td>
    				<a href="${ctx}/cee/ygCeeInfo/form?id=${ygCeeInfo.ids}">修改</a>
					<a href="${ctx}/cee/ygCeeInfo/delete?id=${ygCeeInfo.ids}" onclick="return confirmx('确认要删除该学生信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>