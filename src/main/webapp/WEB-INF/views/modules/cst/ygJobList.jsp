<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Job管理</title>
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
	<br>
	<form:form id="searchForm" modelAttribute="ygJob" action="${ctx}/cst/job/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Skill Level：</label>
				<form:select path="skillLevel" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>Location City：</label>
				<form:input path="locationCity" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>Project Length：</label>
				<form:input path="projectLength" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>Start Date：</label>
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ygJob.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>Out Source：</label>
				<form:input path="outSource" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>Delivery Mode：</label>
				<form:input path="deliveryMode" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>Booked：</label>
				<form:select path="isBook" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cs_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>create_date：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ygJob.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Query"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Skill Leve</th>
				<th>Location City</th>
				<th>Project Length</th>
				<th>Start Date</th>
				<th>Out Source</th>
				<th>Delivery Mode</th>
				<th>Booked</th>
				<th>create_date</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygJob">
			<tr>
				<td>
					${fns:getDictLabel(consultant.skillLevel, 'yg_cs_skill_level', '')}
				</td>
				<td>
					${fns:getDictLabel(consultant.locationCity, 'yg_cs_location_city', '')}
				</td>
				<td>
					${ygJob.projectLength}
				</td>
				<td>
					<fmt:formatDate value="${ygJob.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(consultant.outSource, 'yg_cs_out_yesno', '')}
				</td>
				<td>
					${fns:getDictLabel(consultant.deliveryMode, 'yg_cs_delivery_mode', '')}
				</td>
				<td>
					${fns:getDictLabel(ygJob.isBook, 'yg_cs_yesno', '')}
				</td>
				<td>
					<fmt:formatDate value="${ygJob.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>