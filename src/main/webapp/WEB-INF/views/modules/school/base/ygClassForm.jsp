<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>班级信息管理</title>
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
		<li><a href="${ctx}/school/base/ygClass/">班级信息列表</a></li>
		<li class="active"><a
			href="${ctx}/school/base/ygClass/form?id=${ygClass.id}">班级信息<shiro:hasPermission
					name="school:base:ygClass:edit">${not empty ygClass.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="school:base:ygClass:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ygClass"
		action="${ctx}/school/base/ygClass/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">班级图片：</label>
			<div class="controls">
				<form:hidden id="classAvatar" path="classAvatar" htmlEscape="false"
					maxlength="500" class="input-xlarge" />
				<sys:ckfinder input="classAvatar" type="images" maxWidth="100" maxHeight="100"
					uploadPath="/school/course" selectMultiple="false" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">班级名称：</label>
			<div class="controls">
				<form:input path="className" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">班级地址：</label>
			<div class="controls">
				<form:input path="classAddress" htmlEscape="false" maxlength="255" />

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">班级人数：</label>
			<div class="controls">
				<form:input path="classCount" htmlEscape="false" maxlength="3" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ygClass.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="school:base:ygClass:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>