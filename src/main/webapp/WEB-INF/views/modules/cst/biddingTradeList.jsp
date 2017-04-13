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
	<form:form id="searchForm" modelAttribute="bidding" action="${ctx}/cst/bidding/listTradeBid" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Bidder：</label>
				<form:input path="bidderId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
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
				<th>publisher Name</th>
				<th>publisher Email</th>
				<th>publisher Mobile</th>
				<th>currentBid</th>
				<th>bidder Name</th>
				<th>bidder Email</th>
				<th>bidder Mobile</th>
				<th>bidder Time</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tradeBid">
			<tr>
				<td>
					${tradeBid.publisherName}
				</td>
				<td>
					${tradeBid.publisherEmail}
				</td>
				<td>
					${tradeBid.publisherMobile}
				</td>
				<td>
					${tradeBid.currentBid}
				</td>
				<td>
					${tradeBid.bidderName}
				</td>
				<td>
					${tradeBid.bidderEmail}
				</td>
				<td>
					${tradeBid.bidderMobile}
				</td>
				<td>
					<fmt:formatDate value="${tradeBid.biddingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(tradeBid.formStatus, 'yg_cs_form_status', '')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>