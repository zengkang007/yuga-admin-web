<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业短信配置管理</title>
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

				if ($('#accesskey').val() == '') {
					$('#btnCreate').click(function() {
						$.get('accessId', function(data) {
							$('#accesskey').val(data);
						});
					});
				} else {
					$('#accesskey').attr('readonly', true);
					$('#btnCreate').attr('enable', false);
				}
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sms/companySms/">企业短信配置列表</a></li>
		<li class="active"><a
			href="${ctx}/sms/companySms/form?id=${companySms.id}">企业短信配置<shiro:hasPermission
					name="sms:companySms:edit">${not empty companySms.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="sms:companySms:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="companySms"
		action="${ctx}/sms/companySms/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">企业接口名称：</label>
			<div class="controls">
				<form:input path="companyinf" htmlEscape="false" maxlength="10"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业编号：</label>
			<div class="controls">
				<form:input path="brandid" htmlEscape="false" maxlength="10"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可用条数：</label>
			<div class="controls">
				<form:input path="remainnum" htmlEscape="false" maxlength="11"
					class="input-xlarge  required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">accessKey：</label>
			<div class="controls">
				<form:input path="accesskey" htmlEscape="false" maxlength="255"
					class="input-xlarge  required" />
				<span class="help-inline"><font color="red">*</font></span> <input
					id="btnCreate" class="btn btn-primary" type="button" value="更新" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用接口:</label>
			<div class="controls">
				<form:checkboxes path="infConfigList" items="${allSmsConfig}" itemLabel="gatewayname" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用:</label>
			<div class="controls">
				<form:select path="enable">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sms:companySms:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>