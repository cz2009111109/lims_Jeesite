<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申购单货物管理</title>
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
		<li><a href="${ctx}/material/fsMaterialPurchasingorderlist?entryStatus=1">申购单货物列表</a></li>
		<li class="active"><a href="${ctx}/material/fsMaterialPurchasingorderlist/form?id=${fsMaterialPurchasingorderlist.id}">申购单货物<shiro:hasPermission name="material:fsMaterialPurchasingorderlist:edit">${not empty fsMaterialPurchasingorderlist.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="material:fsMaterialPurchasingorderlist:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fsMaterialPurchasingorderlist" action="${ctx}/material/fsMaterialPurchasingorderlist/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">申购单：</label>
			<div class="controls">
				<form:input path="orderId.id" htmlEscape="false" maxlength="64" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物品：</label>
			<div class="controls">
				<form:input path="materialId.id" htmlEscape="false" maxlength="64" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="materialCode" htmlEscape="false" maxlength="50" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物品名称：</label>
			<div class="controls">
				<form:input path="materialName" htmlEscape="false" maxlength="150" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌：</label>
			<div class="controls">
				<form:input path="brand" htmlEscape="false" maxlength="255" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
				<form:input path="category" htmlEscape="false" maxlength="64" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目录价格：</label>
			<div class="controls">
				<form:input path="catalogprice" htmlEscape="false" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际价格：</label>
			<div class="controls">
				<form:input path="actualprice" htmlEscape="false" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品规格：</label>
			<div class="controls">
				<form:input path="unitnum" htmlEscape="false" maxlength="20" class="input-xlarge digits"  />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:select path="unit" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('material_unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:input path="supplierinfo.id" htmlEscape="false" maxlength="64" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购数量：</label>
			<div class="controls">
				<form:input path="purchNum" htmlEscape="false" maxlength="11" class="input-xlarge digits"  />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总价：</label>
			<div class="controls">
				<form:input path="purchTotalprice" htmlEscape="false" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货物状态：</label>
			<div class="controls">
				<form:select path="purchStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('purchStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发票状态：</label>
			<div class="controls">
				<form:select path="invoiceStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('invoiceStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款状态：</label>
			<div class="controls">
				<form:select path="payStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('payStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" class="input-xlarge " />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库状态：</label>
			<div class="controls">
				<form:select path="entryStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('entryStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="material:fsMaterialPurchasingorderlist:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>