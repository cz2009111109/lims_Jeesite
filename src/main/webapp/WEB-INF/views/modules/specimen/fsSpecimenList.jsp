<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>样本库存管理</title>
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
		<li class="active"><a href="${ctx}/specimen/fsSpecimen/">样本库存列表</a></li>
		<shiro:hasPermission name="specimen:fsSpecimen:edit"><li><a href="${ctx}/specimen/fsSpecimen/form">样本库存添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsSpecimen" action="${ctx}/specimen/fsSpecimen/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>合同编号：</label>
				<form:input path="contractnumber" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>合同名称：</label>
				<form:input path="contractname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>样本类型：</label>
				<form:select path="sampleType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sample_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>送样人姓名：</label>
				<form:input path="samplingName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>物种类型：</label>
				<form:select path="sampleSpecies" class="input-medium">
					<form:option value="" label=""/>
					<form:options  items="${fns:getDictList('sample_species')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>项目类型：</label>
				<form:select path="projectType" class="input-medium">
					<form:option value="" label=""/>
					<form:options  items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>收样人：</label>
				<sys:treeselect id="user" name="user.id" value="${fsSpecimen.user.id}" labelName="user.name" labelValue="${fsSpecimen.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>合同编号</th>
				<th>合同名称</th>
				<th>客户名称</th>
				<th>样本类型 RNA DNA 组织样三种</th>
				<th>送样人姓名</th>
				<th>样本类型(动物 植物 细菌 真菌 其它等)</th>
				<th>项目类型 HIC RNA DNA</th>
				<th>数量(默认管数)</th>
				<th>数量单位（管，只，块）</th>
				<th>存储位置</th>
				<th>收样人</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="specimen:fsSpecimen:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsSpecimen">
			<tr>
				<td><a href="${ctx}/specimen/fsSpecimen/form?id=${fsSpecimen.id}">
					${fsSpecimen.contractnumber}
				</a></td>
				<td>
					${fsSpecimen.contractname}
				</td>
				<td>
					${fsSpecimen.customerName}
				</td>
				<td>
					${fns:getDictLabel(fsSpecimen.sampleType, 'sample_type', '')}
				</td>
				<td>
					${fsSpecimen.samplingName}
				</td>
				<td>
					${fns:getDictLabel(fsSpecimen.sampleSpecies, 'sample_species', '')}
				</td>
				<td>
					${fns:getDictLabel(fsSpecimen.projectType, 'project_type', '')}
				</td>
				<td>
					${fsSpecimen.tubes}
				</td>
				<td>
					${fns:getDictLabel(fsSpecimen.unit, 'unit', '')}
				</td>
				<td>
					${fsSpecimen.fsStorehouse.id}
				</td>
				<td>
					${fsSpecimen.user.name}
				</td>
				<td>
					<fmt:formatDate value="${fsSpecimen.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fsSpecimen.remarks}
				</td>
				<shiro:hasPermission name="specimen:fsSpecimen:edit"><td>
    				<a href="${ctx}/specimen/fsSpecimen/form?id=${fsSpecimen.id}">修改</a>
					<a href="${ctx}/specimen/fsSpecimen/delete?id=${fsSpecimen.id}" onclick="return confirmx('确认要删除该样本库存吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>