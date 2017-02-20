<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>课表基础信息管理</title>
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
		<li><a href="${ctx}/school/base/baseCourse/">课表基础信息列表</a></li>
		<li class="active"><a
			href="${ctx}/school/base/baseCourse/form?id=${baseCourse.id}">课表基础信息<shiro:hasPermission
					name="school:base:baseCourse:edit">${not empty baseCourse.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="school:base:baseCourse:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="baseCourse"
		action="${ctx}/school/base/baseCourse/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">班级名称：</label>
			<div class="controls">
				<form:select path="classId" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getYgClassList()}" itemLabel="className"
						itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星期：</label>
			<div class="controls">
				<form:select path="weekId" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yg_week')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">节次：</label>
			<div class="controls">
				<form:select path="section" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yg_course_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程：</label>
			<div class="controls">
				<form:select path="courseId" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getYgCourseList()}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教师：</label>
			<div class="controls">
				<sys:treeselect id="teacherId" name="teacherId"
					value="${baseCourse.teacherId}" labelName="${baseCourse.user.id}"
					labelValue="${baseCourse.user.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学期开始：</label>
			<div class="controls">
				<input name="monthStart" id="monthStart" path="monthStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${baseCourse.monthStart}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学期结束：</label>
			<div class="controls">
				<input name="monthEnd" path="monthEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${baseCourse.monthEnd}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="school:base:baseCourse:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>