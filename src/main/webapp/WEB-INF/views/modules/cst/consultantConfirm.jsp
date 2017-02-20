<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Consultant form</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('hold on...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("error input ");
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
	<form:form id="inputForm" modelAttribute="consultant" action="${ctx}/cst/consultant/book" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">Skill Level：</label>
			<div class="controls">
				<form:select path="skillLevel" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_cs_skill_level')}" readOnly="true" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Notes：</label>
			<div class="controls">
				<form:textarea path="notes" htmlEscape="false" rows="4" maxlength="1000" readOnly="true"  class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cst:consultant:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Confirm"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="Return" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>