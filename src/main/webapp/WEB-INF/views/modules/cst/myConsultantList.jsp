<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Consultant</title>
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
		<li class="active"><a href="${ctx}/cst/consultant/myConsult">My Consultant List</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="consultant" action="${ctx}/cst/consultant/myConsult" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Skill Level：</label>
				<form:select path="skillLevel" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cs_skill_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>Location City：</label>
				<form:select path="locationCity" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cs_location_city')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>Project Length：</label>
				<form:input path="projectLength" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>Start Date：</label>
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${consultant.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>Out Source：</label>
				<form:select path="outSource" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cs_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>Delivery Mode：</label>
				<form:select path="deliveryMode" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cs_delivery_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>Govt Clearnce：</label>
				<form:select path="govtClearnce" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cs_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>Audit Status：</label>
				<form:select path="formStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cs_form_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>Resource ID</th>
				<th>Skill Level</th>
				<th>Location City</th>
				<th>Project Length</th>
				<th>Start Date</th>
				<th>Out Source</th>
				<th>Delivery Mode</th>
				<th>Govt Clearnce</th>
				<th>Status</th>
				<shiro:hasPermission name="cst:consultant:edit"><th>Operation</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="consultant">
			<tr>
				<td><a href="${ctx}/cst/consultant/form?id=${consultant.id}">
						${consultant.id}
				</a></td>
				<td>${fns:getDictLabel(consultant.skillLevel, 'yg_cs_skill_level', '')}</td>
				<td>
					${fns:getDictLabel(consultant.locationCity, 'yg_cs_location_city', '')}
				</td>
				<td>
					${consultant.projectLength}
				</td>
				<td>
					<fmt:formatDate value="${consultant.startDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${fns:getDictLabel(consultant.outSource, 'yg_cs_out_yesno', '')}
				</td>
				<td>
					${fns:getDictLabel(consultant.deliveryMode, 'yg_cs_delivery_mode', '')}
				</td>
				<td>
					${fns:getDictLabel(consultant.govtClearnce, 'yg_cs_yesno', '')}
				</td>
				<td>
					${fns:getDictLabel(consultant.formStatus, 'yg_cs_form_status', '')}
				</td>
				<shiro:hasPermission name="cst:consultant:edit"><td>
					<a href="${ctx}/cst/consultant/form?id=${consultant.id}">Edit</a>
					<a href="${ctx}/cst/consultant/delete?id=${consultant.id}" onclick="return confirmx('Confirm to delete Consultant？', this.href)">Delete</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>