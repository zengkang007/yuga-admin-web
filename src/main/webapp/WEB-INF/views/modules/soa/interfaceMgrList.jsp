<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>接口管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnRefresh').click(function(){
				$.post('interfaceMgr/refresh',function(data){
					if(data == 'ok'){
						alert('刷新缓存成功!');
					}
				})
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
		<li class="active"><a href="${ctx}/soa/interfaceMgr/">接口列表</a></li>
		<shiro:hasPermission name="soa:interfaceMgr:edit"><li><a href="${ctx}/soa/interfaceMgr/form">接口添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="interfaceMgr" action="${ctx}/soa/interfaceMgr/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>接口类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ksm_interface')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否启用：</label>
				<form:select path="enable" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>启用时间：</label>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${interfaceMgr.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>失效时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${interfaceMgr.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnRefresh" class="btn btn-primary" type="button" value="刷新缓存"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>接口类型</th>
				<th>是否启用</th>
				<th>接口地址</th>
				<th>有效开始时间</th>
				<th>结束时间</th>
				<shiro:hasPermission name="soa:interfaceMgr:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="interfaceMgr">
			<tr>
				<td><a href="${ctx}/soa/interfaceMgr/form?id=${interfaceMgr.id}">
					${fns:getDictLabel(interfaceMgr.type, 'ksm_interface', '')}
				</a></td>
				<td>
					${fns:getDictLabel(interfaceMgr.enable, 'yes_no', '')}
				</td>
				<td>
					${interfaceMgr.url}
				</td>
				<td>
					<fmt:formatDate value="${interfaceMgr.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${interfaceMgr.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="soa:interfaceMgr:edit"><td>
<%-- 					<a href="${ctx}/soa/interfaceMgr/enable?id=${interfaceMgr.id}&enable=1">启用</a>
					<a href="${ctx}/soa/interfaceMgr/enable?id=${interfaceMgr.id}&enable=0">停止</a>
 --%>    				<a href="${ctx}/soa/interfaceMgr/form?id=${interfaceMgr.id}">修改</a>
					<a href="${ctx}/soa/interfaceMgr/delete?id=${interfaceMgr.id}" onclick="return confirmx('确认要删除该接口吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>