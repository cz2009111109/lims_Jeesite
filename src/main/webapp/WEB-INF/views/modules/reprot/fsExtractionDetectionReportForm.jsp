<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提取检测报告管理</title>
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
		<li><a href="${ctx}/reprot/fsExtractionDetectionReport/">提取检测报告列表</a></li>
		<li class="active"><a href="${ctx}/reprot/fsExtractionDetectionReport/form?id=${fsExtractionDetectionReport.id}">提取检测报告<shiro:hasPermission name="reprot:fsExtractionDetectionReport:edit">${not empty fsExtractionDetectionReport.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="reprot:fsExtractionDetectionReport:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fsExtractionDetectionReport" action="${ctx}/reprot/fsExtractionDetectionReport/save" method="post" class="form-horizontal">
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
				<sys:treeselect id="chiefOfficer" name="chiefOfficer.id" value="${fsExtractionDetectionReport.chiefOfficer.id}" labelName="chiefOfficer.name" labelValue="${fsExtractionDetectionReport.chiefOfficer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验负责人：</label>
			<div class="controls">
				<sys:treeselect id="labOfficer" name="labOfficer.id" value="${fsExtractionDetectionReport.labOfficer.id}" labelName="labOfficer.name" labelValue="${fsExtractionDetectionReport.labOfficer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信息负责人：</label>
			<div class="controls">
				<sys:treeselect id="infomationOfficer" name="infomationOfficer.id" value="${fsExtractionDetectionReport.infomationOfficer.id}" labelName="infomationOfficer.name" labelValue="${fsExtractionDetectionReport.infomationOfficer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提取检测任务：</label>
			<div class="controls">
				<form:input path="extractionDetection.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收样人：</label>
			<div class="controls">
				<sys:treeselect id="receiveUser" name="receiveUser.id" value="${fsExtractionDetectionReport.receiveUser.id}" labelName="receiveUser.name" labelValue="${fsExtractionDetectionReport.receiveUser.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收样日期：</label>
			<div class="controls">
				<input name="receiveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsExtractionDetectionReport.receiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">运输情况：</label>
			<div class="controls">
				<form:select path="samplingState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到样状态：</label>
			<div class="controls">
				<form:select path="receiveState" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('receive_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核人：</label>
			<div class="controls">
				<form:input path="auditor.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核时间：</label>
			<div class="controls">
				<input name="auditTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsExtractionDetectionReport.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检测溶液：</label>
			<div class="controls">
				<form:input path="detectionSolution" htmlEscape="false" maxlength="500" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检测内容：</label>
			<div class="controls">
				<form:checkboxes path="detectionType" items="${fns:getDictList('detectionType')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样品管数：</label>
			<div class="controls">
				<form:input path="tubes" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样本类型：</label>
			<div class="controls">
				<form:input path="sampleType" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样品数：</label>
			<div class="controls">
				<form:input path="sampleNumber" htmlEscape="false" maxlength="10" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合格数：</label>
			<div class="controls">
				<form:input path="passnumber" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险数：</label>
			<div class="controls">
				<form:input path="risknumber" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不合格数：</label>
			<div class="controls">
				<form:input path="failnumber" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">与信息单是否相符：</label>
			<div class="controls">
				<form:select path="informationCoincide" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('informationCoincide')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="file" path="file" htmlEscape="false" maxlength="10000" class="input-xlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/reprot/fsExtractionDetectionReport" selectMultiple="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="reprot:fsExtractionDetectionReport:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>