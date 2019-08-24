<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提取检测任务管理</title>
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
		<li><a href="${ctx}/task/fsExtractionDetectionTask/">提取检测任务列表</a></li>
		<li class="active"><a href="${ctx}/task/fsExtractionDetectionTask/form?id=${fsExtractionDetectionTask.id}">提取检测任务<shiro:hasPermission name="task:fsExtractionDetectionTask:edit">${not empty fsExtractionDetectionTask.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="task:fsExtractionDetectionTask:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fsExtractionDetectionTask" action="${ctx}/task/fsExtractionDetectionTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">提取检测任务单号：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户：</label>
			<div class="controls">
				<form:input path="customer" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总项目：</label>
			<div class="controls">
				<form:input path="project.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子项目：</label>
			<div class="controls">
				<form:input path="subproject.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总负责人：</label>
			<div class="controls">
				<sys:treeselect id="chiefOfficer" name="chiefOfficer.id" value="${fsExtractionDetectionTask.chiefOfficer.id}" labelName="chiefOfficer.name" labelValue="${fsExtractionDetectionTask.chiefOfficer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass=""  allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验负责人：</label>
			<div class="controls">
				<sys:treeselect id="labOfficerList" name="labOfficer.id" value="${fsExtractionDetectionTask.labOfficer.id}" labelName="labOfficer.name" labelValue="${fsExtractionDetectionTask.labOfficer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true" checked="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信息负责人：</label>
			<div class="controls">
				<sys:treeselect id="infomationOfficer" name="infomationOfficer.id" value="${fsExtractionDetectionTask.infomationOfficer.id}" labelName="infomationOfficer.name" labelValue="${fsExtractionDetectionTask.infomationOfficer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsExtractionDetectionTask.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsExtractionDetectionTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="file" path="file" htmlEscape="false" maxlength="10000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/task/fsExtractionDetectionTask" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="task:fsExtractionDetectionTask:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>