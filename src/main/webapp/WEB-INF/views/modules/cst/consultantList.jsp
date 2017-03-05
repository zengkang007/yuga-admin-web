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
		<li class="active"><a href="${ctx}/cst/consultant/">Consultant List</a></li></ul>
	<form:form id="searchForm" modelAttribute="consultant" action="${ctx}/cst/consultant/" method="post" class="breadcrumb form-search">
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
				<th>Bids</th>
				<th>Current Bid</th>
				<th>Out Source</th>
				<th>Delivery Mode</th>
				<th>Govt Clearnce</th>
				<th>Submitter</th>
				<th>Bid</th>
				<th>Operate</th>
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
					${consultant.totalBidding}
				</td>
				<td>
					${consultant.currentBid}
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
						${fns:getUserById(consultant.submitter).name}
				</td>
				<td>
					<a href="${ctx}/cst/bidding/bidConfirm?consultantId=${consultant.id}">Place Bid</a>
				</td>
				<td>
					<c:choose>
						<c:when test="${consultant.isBook == 0}">
							<a href="${ctx}/cst/consultant/bookConfirm?id=${consultant.id}">Book</a>
						</c:when>
						<c:otherwise>
							Booked
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>