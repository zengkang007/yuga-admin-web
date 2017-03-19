<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Bidding Manager</title>
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
		<li class="active"><a href="${ctx}/cst/bidding/">Bidding List</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bidding" action="${ctx}/cst/bidding/listBidder" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Current Bid：</label>
				<form:input path="currentBid" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>Consultant Name：</label>
				<form:input path="consultantId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>Bidding Time：</label>
				<input name="biddingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bidding.biddingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>Bidder</th>
				<th>Current Bid</th>
				<th>Consultant Name</th>
				<th>Bidding Time</th>
				<th>Status</th>
				<shiro:hasPermission name="cst:bidding:edit"><th>Operate</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bidding">
			<tr>
				<td>
					${fns:getUserById(bidding.bidderId).name}
				</td>
				<td>
					${bidding.currentBid}
				</td>
				<td>
					${fns:getConsultantById(bidding.consultantId).name}
				</td>
				<td>
					<fmt:formatDate value="${bidding.biddingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(bidding.formStatus, 'yg_cs_form_status', '')}
				</td>
				<shiro:hasPermission name="cst:bidding:edit"><td>
					<a href="${ctx}/cst/bidding/updateBidderStatus?id=${bidding.id}&formStatus=1" onclick="return confirmx('Confirm to Accept Bid？', this.href)">Accept</a>
					|
					<a href="${ctx}/cst/bidding/updateBidderStatus?id=${bidding.id}&formStatus=-1" onclick="return confirmx('Confirm to DENY Bid？', this.href)"><font color="red">Deny</font></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>