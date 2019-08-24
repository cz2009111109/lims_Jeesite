<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>样本出库管理</title>
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
		<li class="active"><a href="${ctx}/specimen/fsSpecimenout/">样本出库列表</a></li>
		<shiro:hasPermission name="specimen:fsSpecimenout:edit"><li><a href="${ctx}/specimen/fsSpecimenout/form">样本出库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsSpecimenout" action="${ctx}/specimen/fsSpecimenout/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>入库样本：</label>
				<form:input path="fsSpecimenin.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>入库样本</th>
				<th>合同编号</th>
				<th>合同名称</th>
				<th>客户名称</th>
				<th>送样人姓名</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="specimen:fsSpecimenout:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsSpecimenout">
			<tr>
				<td><a href="${ctx}/specimen/fsSpecimenout/form?id=${fsSpecimenout.id}">
					${fsSpecimenout.fsSpecimenin.id}
				</a></td>
				<td>
					${fsSpecimenout.contractnumber}
				</td>
				<td>
					${fsSpecimenout.contractname}
				</td>
				<td>
					${fsSpecimenout.customerName}
				</td>
				<td>
					${fsSpecimenout.samplingName}
				</td>
				<td>
					${fsSpecimenout.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fsSpecimenout.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="specimen:fsSpecimenout:edit"><td>
    				<a href="${ctx}/specimen/fsSpecimenout/form?id=${fsSpecimenout.id}">修改</a>
					<a href="${ctx}/specimen/fsSpecimenout/delete?id=${fsSpecimenout.id}" onclick="return confirmx('确认要删除该样本出库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>