<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>样本库存管理</title>
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
		<li><a href="${ctx}/specimen/fsSpecimen/">样本库存列表</a></li>
		<li class="active"><a href="${ctx}/specimen/fsSpecimen/form?id=${fsSpecimen.id}">样本库存<shiro:hasPermission name="specimen:fsSpecimen:edit">${not empty fsSpecimen.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="specimen:fsSpecimen:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fsSpecimen" action="${ctx}/specimen/fsSpecimen/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">合同id(来自CRM)：</label>
			<div class="controls">
				<form:input path="contractid" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库样本：</label>
			<div class="controls">
				<form:input path="fsSpecimenin.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同编号：</label>
			<div class="controls">
				<form:input path="contractnumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同名称：</label>
			<div class="controls">
				<form:input path="contractname" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户id：</label>
			<div class="controls">
				<form:input path="customerId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户名称：</label>
			<div class="controls">
				<form:input path="customerName" htmlEscape="false" maxlength="20" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户邮箱：</label>
			<div class="controls">
				<form:input path="customerEmail" htmlEscape="false" maxlength="100" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户手机：</label>
			<div class="controls">
				<form:input path="customerPhone" htmlEscape="false" maxlength="20" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样本类型 RNA DNA 组织样三种：</label>
			<div class="controls">
				<form:select path="sampleType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sample_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送样人姓名：</label>
			<div class="controls">
				<form:input path="samplingName" htmlEscape="false" maxlength="20" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送样人电话：</label>
			<div class="controls">
				<form:input path="samplingPhone" htmlEscape="false" maxlength="20" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送样人邮箱：</label>
			<div class="controls">
				<form:input path="samplingEmail" htmlEscape="false" maxlength="100" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送样状态（干冰 常温）：</label>
			<div class="controls">
				<form:input path="samplingState" htmlEscape="false" maxlength="10" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样本类型(动物 植物 细菌 真菌 其它等)：</label>
			<div class="controls">
				<form:select path="sampleSpecies" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sample_species')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样本目的：</label>
			<div class="controls">
				<form:input path="sampleTarget" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采样前状态：</label>
			<div class="controls">
				<form:select path="samplingBeforestate" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sampling_beforestate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样本编号（组织编号）：</label>
			<div class="controls">
				<form:input path="sampleSerialnumber" htmlEscape="false" maxlength="30" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样本名称(或组织名称)：</label>
			<div class="controls">
				<form:input path="sampleName" htmlEscape="false" maxlength="50" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物种名称(如蓝藻 牛)：</label>
			<div class="controls">
				<form:input path="speciesName" htmlEscape="false" maxlength="50" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目类型 HIC RNA DNA：</label>
			<div class="controls">
				<form:checkboxes path="projectType" items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量(默认管数)：</label>
			<div class="controls">
				<form:input path="tubes" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量单位（管，只，块）：</label>
			<div class="controls">
				<form:select path="unit" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数1 组织部位  Nanodrop ng/uL：</label>
			<div class="controls">
				<form:input path="param1" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数2 组织量 Qubit ng/ul  RIN：</label>
			<div class="controls">
				<form:input path="param2" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数3 体积 uL  提取说明：</label>
			<div class="controls">
				<form:input path="param3" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数4 溶解状态  组织样品状态：</label>
			<div class="controls">
				<form:input path="param4" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否经过DNase处理：</label>
			<div class="controls">
				<form:input path="param5" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">param6：</label>
			<div class="controls">
				<form:input path="param6" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">param7：</label>
			<div class="controls">
				<form:input path="param7" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测序平台：</label>
			<div class="controls">
				<form:select path="param8" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('platform')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">param9：</label>
			<div class="controls">
				<form:input path="param9" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">param10：</label>
			<div class="controls">
				<form:input path="param10" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样本库存状态(0表示未用完,1表示用完)：</label>
			<div class="controls">
				<form:input path="state" htmlEscape="false" maxlength="1" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提取说明：</label>
			<div class="controls">
				<form:input path="extractionDescription" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">存储位置：</label>
			<div class="controls">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收样人：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${fsSpecimen.user.id}" labelName="user.name" labelValue="${fsSpecimen.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="specimen:fsSpecimen:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>