<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
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
		<li><a href="${ctx}/school/base/ygTeacherUser/">教师信息列表</a></li>
		<li class="active"><a href="${ctx}/school/base/ygTeacherUser/form?id=${ygTeacherUser.id}">教师信息<shiro:hasPermission name="school:base:ygTeacherUser:edit">${not empty ygTeacherUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="school:base:ygTeacherUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ygTeacherUser" action="${ctx}/school/base/ygTeacherUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${ygTeacherUser.user.id}" labelName="user.name" labelValue="${ygTeacherUser.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属班级：</label>
			<div class="controls">
				<form:select id="classId" path="classId" class="input-xlarge">
					<form:option value="-1" label="无班级"/>
					<form:options items="${fns:getGradeList()}" itemLabel="name" itemValue="id" title="clas" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="school:base:ygTeacherUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>