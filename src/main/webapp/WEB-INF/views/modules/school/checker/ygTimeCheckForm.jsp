<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>巡检时间主表管理</title>
<meta name="decorator" content="default" />
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
		
		function addRow(list, idx, tpl, row) {
			$(list).append(Mustache.render(tpl, {
				idx : idx,
				delBtn : true,
				row : row
			}));
			$(list + idx).find("select").each(function() {
				$(this).val($(this).attr("data-value"));
			});
			$(list + idx).find("input[type='checkbox'], input[type='radio']").each(
					function() {
						var ss = $(this).attr("data-value").split(',');
						for (var i = 0; i < ss.length; i++) {
							if ($(this).val() == ss[i]) {
								$(this).attr("checked", "checked");
							}
						}
					});
		}
		function delRow(obj, prefix) {
			var id = $(prefix + "_id");
			var delFlag = $(prefix + "_delFlag");
			if (id.val() == "") {
				$(obj).parent().parent().remove();
			} else if (delFlag.val() == "0") {
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			} else if (delFlag.val() == "1") {
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/school/checker/ygTimeCheck/">巡检时间主表列表</a></li>
		<li class="active"><a
			href="${ctx}/school/checker/ygTimeCheck/form?id=${ygTimeCheck.id}">巡检时间主表<shiro:hasPermission
					name="school:checker:ygTimeCheck:edit">${not empty ygTimeCheck.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="school:checker:ygTimeCheck:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ygTimeCheck"
		action="${ctx}/school/checker/ygTimeCheck/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" value="${ygTimeCheck.id}"/>
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50"
					class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span class="help-inline">填写巡检时间方案名称。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="${ygTimeCheck.startTime}"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate " value="${ygTimeCheck.endTime}"
					onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">添加详情：</label>
			<div class="controls">
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th class="hide"></th>
							<th>名称</th>
							<th>勾选分数</th>
							<th>默认分数</th>
							<th>附加值</th>
							<shiro:hasPermission name="admin:course:edit">
								<th width="10">&nbsp;</th>
							</shiro:hasPermission>
						</tr>
					</thead>
					<tbody id="ygTimeFieldList">
					</tbody>
					<shiro:hasPermission name="school:checker:ygTimeCheck:edit">
						<tfoot>
							<tr>
								<td colspan="4"><a href="javascript:"
									onclick="addRow('#ygTimeFieldList', ygTimeFieldRowIdx, ygTimeFieldTpl);ygTimeFieldRowIdx = ygTimeFieldRowIdx + 1;"
									class="btn">新增</a></td>
							</tr>
						</tfoot>
					</shiro:hasPermission>
				</table>
				<script type="text/template" id="ygTimeFieldTpl">//<!--
						<tr id="ygTimeFieldList{{idx}}">
							<td class="hide">
								<input id="ygTimeFieldList{{idx}}_id" name="ygTimeFieldList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="ygTimeFieldList{{idx}}_delFlag" name="ygTimeFieldList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="ygTimeFieldList{{idx}}_name" name="ygTimeFieldList[{{idx}}].fieldName" type="text" value="{{row.fieldName}}" maxlength="45" class="input-small required"/>
								<span class="help-inline"><font color="red">*</font> </span>
								<span class="help-inline">填写巡检项目项目。</span>
							</td>
							<td>
								<input id="ygTimeFieldList{{idx}}_mark" name="ygTimeFieldList[{{idx}}].mark" type="text" value="{{row.mark}}" maxlength="5" class="input-small required"/>
								<span class="help-inline"><font color="red">*</font> </span>
								<span class="help-inline">填写巡检项目分数。</span>
							</td>
							<td>
								<input id="ygTimeFieldList{{idx}}_defaultMark" name="ygTimeFieldList[{{idx}}].defaultMark" type="text" value="{{row.defaultMark}}" maxlength="5" class="input-small required"/>
								<span class="help-inline"><font color="red">*</font> </span>
								<span class="help-inline">填写巡检项目默认分数。</span>
							</td>
							<td>
								<input id="ygTimeFieldList{{idx}}_filedValue" name="ygTimeFieldList[{{idx}}].filedValue" type="text" value="{{row.filedValue}}" maxlength="10" class="input-small"/>
								<span class="help-inline">填写该字段对应可能的值。</span>
							</td>
							<shiro:hasPermission name="school:checker:ygTimeCheck:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#ygTimeFieldList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
				<script type="text/javascript">
						var ygTimeFieldRowIdx = 0, ygTimeFieldTpl = $("#ygTimeFieldTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(ygTimeCheck.ygTimeFieldList)};
							for (var i=0; i<data.length; i++){
								addRow('#ygTimeFieldList', ygTimeFieldRowIdx, ygTimeFieldTpl, data[i]);
								ygTimeFieldRowIdx = ygTimeFieldRowIdx + 1;
							}
						});
					</script>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="school:checker:ygTimeCheck:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>