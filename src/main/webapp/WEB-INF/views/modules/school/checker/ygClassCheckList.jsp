<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡查情况管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/school/checker/ygClassCheck/">巡查情况列表</a></li>
		<shiro:hasPermission name="school:checker:ygClassCheck:edit"><li><a href="${ctx}/school/checker/ygClassCheck/form">巡查情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ygClassCheck" action="${ctx}/school/checker/ygClassCheck/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>班级：</label>
				<form:select path="classId" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getYgClassList()}" itemLabel="className"
						itemValue="id" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>周：</label>
				<form:select path="weekId" class="input-medium"  name="weekId" id="weekId">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('yg_week')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>节次：</label>
				<form:select path="sectionId" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('yg_course_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>课程：</label>
				<form:select path="courseId" class="input-medium" >
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getYgCourseList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>教师：</label>
				<sys:treeselect id="teacherId" name="teacherId" value="${ygClassCheck.teacherId}" labelName="${ygClassCheck.user.id}" labelValue="${ygClassCheck.user.name}"
					title="教师" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="document.getElementById('searchForm').action='${ctx}/school/checker/ygClassCheck/dowload';document.getElementById('searchForm').submit();"  value="下载"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>班级</th>
				<th>周</th>
				<th>章节</th>
				<th>课程</th>
				<th>教师</th>
				<th>总分</th>
				<shiro:hasPermission name="school:checker:ygClassCheck:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ygClassCheck">
			<tr>
				<td><a href="${ctx}/school/checker/ygClassCheck/form?id=${ygClassCheck.id}">
					${fns:getYgClassLabel(ygClassCheck.classId)}
				</a></td>
				<td>
					${fns:getDictLabel(ygClassCheck.weekId, 'yg_week', defaultValue)}
				</td>
				<td>
					${fns:getDictLabel(ygClassCheck.sectionId, 'yg_course_no', defaultValue)}
				</td>
				<td>
					${fns:getYgCourseListLabel(ygClassCheck.courseId)}
				</td>
				<td>
					${fns:getUserById(ygClassCheck.teacherId).name}
				</td>
				<td>
					${ygClassCheck.totalMark}
				</td>
				<shiro:hasPermission name="school:checker:ygClassCheck:edit"><td>
    				<a href="${ctx}/school/checker/ygClassCheck/form?id=${ygClassCheck.id}">修改</a>
					<a href="${ctx}/school/checker/ygClassCheck/delete?id=${ygClassCheck.id}" onclick="return confirmx('确认要删除该巡查情况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>