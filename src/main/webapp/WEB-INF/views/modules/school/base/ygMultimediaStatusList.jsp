<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>多媒体使用情况管理</title>
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
		<li class="active"><a href="${ctx}/school/base/ygMultimediaStatus/">多媒体使用情况列表</a></li>
		<shiro:hasPermission name="school:base:ygMultimediaStatus:edit"><li><a href="${ctx}/school/base/ygMultimediaStatus/form">多媒体使用情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygMultimediaStatus" action="${ctx}/school/base/ygMultimediaStatus/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>产品编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>生产时间：</label>
				<input name="productDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ygMultimediaStatus.productDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>设备状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_multimedia_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>使用者姓名：</label>
				<form:input path="owner" htmlEscape="false" maxlength="5" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备名称</th>
				<th>产品编号</th>
				<th>生产时间</th>
				<th>设备状态</th>
				<th>使用者姓名</th>
				<shiro:hasPermission name="school:base:ygMultimediaStatus:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygMultimediaStatus">
			<tr>
				<td><a href="${ctx}/school/base/ygMultimediaStatus/form?id=${ygMultimediaStatus.id}">
					${ygMultimediaStatus.name}
				</a></td>
				<td>
					${ygMultimediaStatus.code}
				</td>
				<td>
					<fmt:formatDate value="${ygMultimediaStatus.productDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(ygMultimediaStatus.status, 'yg_multimedia_status', '')}
				</td>
				<td>
					${ygMultimediaStatus.owner}
				</td>
				<shiro:hasPermission name="school:base:ygMultimediaStatus:edit"><td>
    				<a href="${ctx}/school/base/ygMultimediaStatus/form?id=${ygMultimediaStatus.id}">修改</a>
					<a href="${ctx}/school/base/ygMultimediaStatus/delete?id=${ygMultimediaStatus.id}" onclick="return confirmx('确认要删除该多媒体使用情况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>