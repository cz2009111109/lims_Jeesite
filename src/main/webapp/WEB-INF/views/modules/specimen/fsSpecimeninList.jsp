<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>样本入库管理</title>
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
		<li class="active"><a href="${ctx}/specimen/fsSpecimenin/">样本入库列表</a></li>
		<shiro:hasPermission name="specimen:fsSpecimenin:edit"><li><a href="${ctx}/specimen/fsSpecimenin/form">样本入库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsSpecimenin" action="${ctx}/specimen/fsSpecimenin/" method="post" class="breadcrumb form-search">
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
			<li><label>送样人姓名：</label>
				<form:input path="samplingName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>测序平台：</label>
				<form:select path="param8" class="input-medium">
					<form:option value="" label=""/>
					<form:options  items="${fns:getDictList('platform')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>收样人：</label>
				<sys:treeselect id="user" name="user.id" value="${fsSpecimenin.user.id}" labelName="user.name" labelValue="${fsSpecimenin.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>备注信息：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/><br/>
			</li>
			<li><label>合同id：</label>
				<form:input path="contractid" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th>送样人姓名</th>
				<th>数量(默认管数)</th>
				<th>数量单位（管，只，块）</th>
				<th>测序平台</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<th>合同id(来自CRM)</th>
				<shiro:hasPermission name="specimen:fsSpecimenin:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsSpecimenin">
			<tr>
				<td><a href="${ctx}/specimen/fsSpecimenin/form?id=${fsSpecimenin.id}">
					${fsSpecimenin.contractnumber}
				</a></td>
				<td>
					${fsSpecimenin.contractname}
				</td>
				<td>
					${fsSpecimenin.customerName}
				</td>
				<td>
					${fsSpecimenin.samplingName}
				</td>
				<td>
					${fsSpecimenin.tubes}
				</td>
				<td>
					${fsSpecimenin.unit}
				</td>
				<td>
					${fns:getDictLabels(fsSpecimenin.param8, 'platform', '')}
				</td>
				<td>
					${fsSpecimenin.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fsSpecimenin.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fsSpecimenin.contractid}
				</td>
				<shiro:hasPermission name="specimen:fsSpecimenin:edit"><td>
    				<a href="${ctx}/specimen/fsSpecimenin/form?id=${fsSpecimenin.id}">修改</a>
					<a href="${ctx}/specimen/fsSpecimenin/delete?id=${fsSpecimenin.id}" onclick="return confirmx('确认要删除该样本入库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>