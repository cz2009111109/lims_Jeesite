<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>子项目管理管理</title>
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
		<li><a href="${ctx}/project/fsSubproject/">子项目管理列表</a></li>
		<li class="active"><a href="${ctx}/project/fsSubproject/form?id=${fsSubproject.id}">子项目管理<shiro:hasPermission name="project:fsSubproject:edit">${not empty fsSubproject.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="project:fsSubproject:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fsSubproject" action="${ctx}/project/fsSubproject/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">子项目编号：</label>
			<div class="controls">
				<form:input path="subprojectNumber" htmlEscape="false" maxlength="100" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子项目名称：</label>
			<div class="controls">
				<form:input path="subprojectName" htmlEscape="false" maxlength="500" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同：</label>
			<div class="controls">
				<form:input path="contractid" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总项目：</label>
			<div class="controls">
				<form:select path="fsProject.id" class="input-xlarge" >
					<form:options items="${fsProjects}" itemLabel="projectNumber" itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售id：</label>
			<div class="controls">
				<form:input path="salesperson" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测序平台：</label>
			<div class="controls">
				<form:select path="platform" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('platform')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子项目类型：</label>
			<div class="controls">
				<form:select path="subprojectType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子项目费用：</label>
			<div class="controls">
				<form:input path="subprojectPrice" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回款金额：</label>
			<div class="controls">
				<form:input path="receivingorderMonney" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子项目周期：</label>
			<div class="controls">
				<form:input path="subprojectCycle" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">lims系统用户1：</label>
			<div class="controls">
				<sys:treeselect id="limsUserOne" name="limsUserOne.id" value="${fsSubproject.limsUserOne.id}" labelName="limsUserOne.name" labelValue="${fsSubproject.limsUserOne.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">lims系统用户2：</label>
			<div class="controls">
				<sys:treeselect id="limsUserTwo" name="limsUserTwo.id" value="${fsSubproject.limsUserTwo.id}" labelName="limsUserTwo.name" labelValue="${fsSubproject.limsUserTwo.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子项目管理：</label>
			<div class="controls">
				<sys:treeselect id="subprojectAdmin" name="subprojectAdmin.id" value="${fsSubproject.subprojectAdmin.id}" labelName="subprojectAdmin.name" labelValue="${fsSubproject.subprojectAdmin.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子项目状态：</label>
			<div class="controls">
				<form:select path="subprojectState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('subprojectState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">暂停开始时间：</label>
			<div class="controls">
				<input name="pauseStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsSubproject.pauseStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">暂停停止时间：</label>
			<div class="controls">
				<input name="pauseEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsSubproject.pauseEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计划开始时间：</label>
			<div class="controls">
				<input name="planStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsSubproject.planStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计划结束时间：</label>
			<div class="controls">
				<input name="planEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsSubproject.planEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际开始时间：</label>
			<div class="controls">
				<input name="actualStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsSubproject.actualStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际结束时间：</label>
			<div class="controls">
				<input name="actualEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsSubproject.actualEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="subproject" path="subproject" htmlEscape="false" maxlength="10000" class="input-xlarge"/>
				<sys:ckfinder input="subproject" type="files" uploadPath="/project/fsSubproject" selectMultiple="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="project:fsSubproject:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>