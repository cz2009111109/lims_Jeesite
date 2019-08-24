<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理管理</title>
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
		<li><a href="${ctx}/project/fsProject/">项目管理列表</a></li>
		<li class="active"><a href="${ctx}/project/fsProject/form?id=${fsProject.id}">项目管理<shiro:hasPermission name="project:fsProject:edit">${not empty fsProject.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="project:fsProject:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fsProject" action="${ctx}/project/fsProject/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目编号：</label>
			<div class="controls">
				<form:input path="projectNumber" htmlEscape="false" maxlength="60" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="projectName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
				<input name="projectName" value="${fsProject.projectName}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同id：</label>
			<div class="controls">
				<form:input path="contractId" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同编号：</label>
			<div class="controls">
				<form:input path="contractNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同名称：</label>
			<div class="controls">
				<form:input path="contractName" htmlEscape="false" maxlength="300" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售：</label>
			<div class="controls">
				<form:input path="salesperson.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售名称：</label>
			<div class="controls">
				<form:input path="salespersonName" htmlEscape="false" maxlength="30" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测序平台：</label>
			<div class="controls">
				<form:checkboxes path="platformList" items="${fns:getDictList('platform')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目类型  HIC RNA DNA 二代 三代：</label>
			<div class="controls">
				<form:checkboxes path="projectTypeList" items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同金额：</label>
			<div class="controls">
				<form:input path="contractPrice" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回款金额：</label>
			<div class="controls">
				<form:input path="receivingorderMoney" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目周期：</label>
			<div class="controls">
				<form:input path="projectCycle" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目管理员：</label>
			<div class="controls">
				<sys:treeselect id="projectAdmin" name="projectAdmin.id" value="${fsProject.projectAdmin.id}" labelName="projectAdmin.name" labelValue="${fsProject.projectAdmin.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true" checked="true" />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总项目状态：</label>
			<div class="controls">
				<form:input path="projectState" htmlEscape="false" maxlength="20" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目状态变更时间：</label>
			<div class="controls">
				<input name="projectStateChangetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.projectStateChangetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结题日期：</label>
			<div class="controls">
				<input name="projectSuccessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.projectSuccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目金额：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提前款：</label>
			<div class="controls">
				<form:input path="moneyAdvance" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中款：</label>
			<div class="controls">
				<form:input path="moneyMid" htmlEscape="false" class="input-xlarge digits"   />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">尾款：</label>
			<div class="controls">
				<form:input path="moneyFinal" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到样时间：</label>
			<div class="controls">
				<input name="arriveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.arriveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否提前启动：</label>
			<div class="controls">
				<form:select path="startInAdvance" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提起启动日期：</label>
			<div class="controls">
				<input name="startInAdvanceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.startInAdvanceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">暂停开始时间：</label>
			<div class="controls">
				<input name="pauseStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.pauseStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">暂停停止时间：</label>
			<div class="controls">
				<input name="pauseEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.pauseEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计划开始时间：</label>
			<div class="controls">
				<input name="planStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.planStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计划结束时间：</label>
			<div class="controls">
				<input name="planEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.planEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际开始时间：</label>
			<div class="controls">
				<input name="actualStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.actualStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际结束时间：</label>
			<div class="controls">
				<input name="actualEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsProject.actualEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="projectfile" path="projectfile" htmlEscape="false" maxlength="10000" class="input-xlarge"/>
				<sys:ckfinder input="projectfile" type="files" uploadPath="/project/fsProject" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="project:fsProject:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>