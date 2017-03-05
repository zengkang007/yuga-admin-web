<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>User Registration</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#btnSubmit').click(function(){
                $("#inputForm").submit();
                setOfficeId();
			});

			<%--$("#inputForm").validate({--%>
				<%--rules: {--%>
					<%--loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}--%>
				<%--},--%>
				<%--messages: {--%>
					<%--loginName: {remote: "用户登录名已存在"},--%>
					<%--confirmNewPassword: {equalTo: "输入与上面相同的密码"}--%>
				<%--},--%>
				<%--submitHandler: function(form){--%>
				    <%--debugger;--%>
					<%--loading('正在提交，请稍等...');--%>
					<%--alert('xx')--%>
					<%--form.submit();--%>
				<%--},--%>
				<%--errorContainer: "#messageBox",--%>
				<%--errorPlacement: function(error, element) {--%>
					<%--$("#messageBox").text("输入有误，请先更正。");--%>
					<%--if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){--%>
						<%--error.appendTo(element.parent().parent());--%>
					<%--} else {--%>
						<%--error.insertAfter(element);--%>
					<%--}--%>
				<%--}--%>
//			});
		});

		function setOfficeId() {
            document.getElementById('office.id').value = document.getElementById('company.id').value;
        }
		function onChangeOfficeId(){
            setOfficeId();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">

	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="/b/register" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="office.id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">Name:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Login name:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
				<form:input path="loginName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Password:</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Confirm Password:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Role:</label>
			<div class="controls">
				<form:select path="roleIdList" class="input-xlarge">
					<form:option value="-1" label="Choose"/>
					<form:options items="${fns:getRegisterRoleList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Company:</label>
			<div class="controls">
				<form:select path="company.id" onchange="onChangeOfficeId()" class="input-xlarge">
					<form:options items="${fns:getRegisterOfficeList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">Department:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select path="office.id" class="input-xlarge">--%>
					<%--<form:option value="-1" label="Choose"/>--%>
					<%--<form:options items="${fns:getOfficeList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">Email:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Telephone:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Mobile:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Description:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<c:if test="${not empty user.id}">
			<div class="control-group">
				<label class="control-label">Create Time:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Last Login:</label>
				<div class="controls">
					<label class="lbl">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;Time：<fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="Registe"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="Return" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>