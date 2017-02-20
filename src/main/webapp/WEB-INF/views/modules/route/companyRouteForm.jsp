<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>软路由管理</title>
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

				if ($('#brandId').val() == '') {
					$('#btnCreate').click(function() {
						$.get('brandId', function(data) {
							$('#brandId').val(data);
						});
					});
				} else {
					$('#brandId').attr('readonly', true);
					$('#btnCreate').attr('enable', false);
				}
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/route/">品牌列表</a></li>
		<li class="active"><a
			href="${ctx}/route/form?id=${companyRoute.id}">品牌<shiro:hasPermission
					name="route:companyRoute:edit">${not empty companyRoute.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="route:companyRoute:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="companyRoute"
		action="${ctx}/route/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">logo地址：</label>
			<div class="controls">
				<form:hidden id="logo" path="logo" htmlEscape="false"
					maxlength="500" class="input-xlarge" />
				<sys:ckfinder input="logo" type="files" uploadPath="/route"
					selectMultiple="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:select path="enable" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌编号：</label>
			<div class="controls">
				<form:input path="brandId" htmlEscape="false" maxlength="30"
					value="${companyRoute.brandId}" class="input-xlarge " />
				<input id="btnCreate" class="btn btn-primary" type="button"
					value="更新" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">显示子标题：</label>
			<div class="controls">
				<form:input path="subtitle" htmlEscape="false" maxlength="30"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本号：</label>
			<div class="controls">
				<form:input path="version" htmlEscape="false" maxlength="10"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转发IP：</label>
			<div class="controls">
				<form:input path="ipaddr" htmlEscape="false" maxlength="30"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:input path="comment" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="route:companyRoute:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>