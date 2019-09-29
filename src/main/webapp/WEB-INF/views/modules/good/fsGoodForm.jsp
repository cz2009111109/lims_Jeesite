<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品列表管理</title>
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
		<li><a href="${ctx}/good/fsGood/">商品列表列表</a></li>
		<li class="active"><a href="${ctx}/good/fsGood/form?id=${fsGood.id}">商品列表<shiro:hasPermission name="good:fsGood:edit">${not empty fsGood.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="good:fsGood:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fsGood" action="${ctx}/good/fsGood/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌：</label>
			<div class="controls">
				<form:input path="brand" htmlEscape="false" maxlength="300" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="itemnumber" htmlEscape="false" maxlength="300" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目录价格：</label>
			<div class="controls">
				<form:input path="catalogprice" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际价格：</label>
			<div class="controls">
				<form:input path="actualprice" htmlEscape="false" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="state" htmlEscape="false" maxlength="1" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品规格：</label>
			<div class="controls">
				<form:input path="unitnum" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:select path="unit" class="input-medium">
					<form:option value="" label=""/>
					<form:options  items="${fns:getDictList('material_unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出厂厂家：</label>
			<div class="controls">
				<form:input path="factory" htmlEscape="false" maxlength="200" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">功能用途：</label>
			<div class="controls">
				<form:input path="function" htmlEscape="false" maxlength="300" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="300" class="input-xlarge "/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
				<form:select path="category" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('good_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:select path="supplierinfo.id" class="input-xlarge" >
					<form:option value="" label="请选择"/>
					<form:options items="${supplierinfos}" itemLabel="name" itemValue="id" htmlEscape="false" />
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
			<shiro:hasPermission name="good:fsGood:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>