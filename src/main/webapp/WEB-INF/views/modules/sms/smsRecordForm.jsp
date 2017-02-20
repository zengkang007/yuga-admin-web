<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>短信记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sms/smsRecord/">短信记录列表</a></li>
		<li class="active"><a
			href="${ctx}/sms/smsRecord/form?id=${smsRecord.id}">短信记录<shiro:hasPermission
					name="sms:smsRecord:edit">${not empty smsRecord.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="sms:smsRecord:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="smsRecord"
		action="${ctx}/sms/smsRecord/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">企业编号：</label>
			<div class="controls">
				<form:input path="brandid" htmlEscape="false" maxlength="50" class="input-xlarge" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">耗时时间：</label>
			<div class="controls">
				<form:input path="elapsetime" htmlEscape="false" class="input-xlarge  digits" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送时间：</label>
			<div class="controls">
				<form:input path="createtime" htmlEscape="false" maxlength="50" class="input-xlarge" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送状态：</label>
			<div class="controls">
				<form:textarea path="status" htmlEscape="false" maxlength="50" class="input-xlarge" readonly="true" />
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>