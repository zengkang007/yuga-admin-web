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
<br><br>
	<form:form id="inputForm" modelAttribute="consultant" action="${ctx}/cst/consultant/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">Consultant Name：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="11" class="input-xlarge "/>
				<span class="help-inline">Your name will not display. </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Floor price：</label>
			<div class="controls">
				<span class="add-on">$</span>
				<form:input path="baseGrade" htmlEscape="false" maxlength="5" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span class="help-inline">Enter the floor price  </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Skill Level：</label>
			<div class="controls">
				<form:select path="skillLevel" class="input-xlarge ">
					<form:option value="-1" label="Default"/>
					<form:options items="${fns:getDictList('yg_cs_skill_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Location City：</label>
			<div class="controls">
				<form:select path="locationCity" class="input-xlarge ">
					<form:option value="-1" label="Default"/>
					<form:options items="${fns:getDictList('yg_cs_location_city')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Project Length：</label>
			<div class="controls">
				<form:select path="projectType" class="input-xlarge ">
					<form:option value="-1" label="Default"/>
					<form:options items="${fns:getDictList('yg_cs_project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<form:input path="projectLength" htmlEscape="false" maxlength="11" class="input-mini"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Experience Length：</label>
			<div class="controls">
				<form:select path="experienceLen" class="input-xlarge ">
					<form:option value="-1" label="Default"/>
					<form:options items="${fns:getDictList('yg_cs_experience')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
				<span class="help-inline">Enter the experience year.  </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Start Date：</label>
			<div class="controls">
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${consultant.startDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Out Source：</label>
			<div class="controls">
				<form:select path="outSource" class="input-xlarge ">
					<form:option value="-1" label="Default"/>
					<form:options items="${fns:getDictList('yg_cs_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Delivery Mode：</label>
			<div class="controls">
				<form:select path="deliveryMode" class="input-xlarge ">
					<form:option value="-1" label="Default"/>
					<form:options items="${fns:getDictList('yg_cs_delivery_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Govt Clearnce：</label>
			<div class="controls">
				<form:select path="govtClearnce" class="input-xlarge ">
					<form:option value="-1" label="Default"/>
					<form:options items="${fns:getDictList('yg_cs_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Notes：</label>
			<div class="controls">
				<form:textarea path="notes" htmlEscape="false" rows="4" maxlength="1000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cst:consultant:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Save"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="Return" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>