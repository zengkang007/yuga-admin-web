<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡查情况管理</title>
	<meta name="decorator" content="default"/>
	<%-- <%@ include file="/WEB-INF/views/include/common.jsp"%> --%>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#classId').change(function(data){
				var classId = $('#classId').val();
				var weekId = $('#weekId').val();
				var sectionId = $('#sectionId').val();
				var monthStart = $('#monthStart').val();
				var monthEnd = $('#monthEnd').val();
				//准备请求后台数据
				//通过 classId, weekId, sectionId,请求课程和教师
				$.ajax({
	                type:"get",
	                cache:false,
	                contentType:"application/x-www-form-urlencoded:charset=UTF-8",
	                url:'${ctx}/school/base/baseCourse/getCourseInfo?qType=1&classId=' + classId
						+ "&weekId=" + weekId + "&section=" + sectionId
						+ '&monthStart=' + monthStart + "&monthEnd=" + monthEnd,
	                dataType:"json",
	                success:function(data){

	                	if(data != undefined) {
		                	$("#courseId").val(data.courseId)//设置值
							$('#courseId').trigger('chosen:updated');//更新选项
		                	$("#teacherIdId").val(data.teacherId);
							$('#teacherId').val();
		            		$("#teacherIdName").val(data.user.name);
	                	} else {
	                		$("#courseId").val("-1")//设置值
							$('#courseId').trigger('chosen:updated');//更新选项	               
	                		$("#teacherIdId").val('');
		            		$("#teacherIdName").val('');
	                	}
						
	                }
	            })
			});
		
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/school/checker/ygClassCheck/">巡查情况列表</a></li>
		<li class="active"><a href="${ctx}/school/checker/ygClassCheck/form?id=${ygClassCheck.id}">巡查情况<shiro:hasPermission name="school:checker:ygClassCheck:edit">${not empty ygClassCheck.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="school:checker:ygClassCheck:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ygClassCheck" action="${ctx}/school/checker/ygClassCheck/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="checkTimeId" value="${ygClassCheck.timeCheck.id}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">班级：</label>
			<div class="controls">
				<form:select path="classId" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getYgClassList()}" itemLabel="className"
						itemValue="id" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">周：</label>
			<div class="controls">
				<form:select path="weekId" name="weekId" id="weekId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_week')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">章节：</label>
			<div class="controls">
				<form:select path="sectionId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yg_course_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程：</label>
			<div class="controls">
				<form:select path="courseId" class="input-xlarge ">
					<form:option value="-1" label=""/>
					<form:options items="${fns:getYgCourseList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教师：</label>
			<div class="controls">
				<sys:treeselect id="teacherId" name="teacherId" value="${ygClassCheck.teacherId}" labelName="${ygClassCheck.user.id}" labelValue="${ygClassCheck.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡查项目:</label>
			<div class="controls">
				<form:checkboxes path="checkItemsList"
						items="${ygClassCheck.timeCheck.ygTimeFieldList}"
						itemLabel="fieldName" itemValue="id" htmlEscape="false"
						class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺勤人数：</label>
			<div class="controls">
				<form:select path="totalAbsence" class="input-xlarge ">
					<form:option value="50" data="0" label="无" />
					<form:option value="40" data="1" label="1个" />
					<form:option value="30" data="2" label="2个" />
					<form:option value="20" data="3" label="3个" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡查人：</label>
			<div class="controls">
				<input value="${fns:getUser().name}" type="text" readonly="readonly" />
			</div>
		</div>
	<%-- 	<div class="control-group">
			<label class="control-label">保留字段1：</label>
			<div class="controls">
				<form:input path="condition1" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保留字段2：</label>
			<div class="controls">
				<form:input path="condition2" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保留字段3：</label>
			<div class="controls">
				<form:input path="condition3" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="school:checker:ygClassCheck:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>