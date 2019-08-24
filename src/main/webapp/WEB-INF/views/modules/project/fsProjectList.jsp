<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理管理</title>
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
		<li class="active"><a href="${ctx}/project/fsProject/">项目管理列表</a></li>
		<shiro:hasPermission name="project:fsProject:edit"><li><a href="${ctx}/project/fsProject/form">项目管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsProject" action="${ctx}/project/fsProject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目编号：</label>
				<form:input path="projectNumber" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="projectName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>合同id：</label>
				<form:input path="contractId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>合同编号：</label>
				<form:input path="contractNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>合同名称：</label>
				<form:input path="contractName" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>备注信息：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目编号</th>
				<th>项目名称</th>
				<th>合同id</th>
				<th>合同编号</th>
				<th>合同名称</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="project:fsProject:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsProject">
			<tr>
				<td><a href="${ctx}/project/fsProject/form?id=${fsProject.id}">
					${fsProject.projectNumber}
				</a></td>
				<td>
					${fsProject.projectName}
				</td>
				<td>
					${fsProject.contractId}
				</td>
				<td>
					${fsProject.contractNumber}
				</td>
				<td>
					${fsProject.contractName}
				</td>
				<td>
					${fsProject.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fsProject.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="project:fsProject:edit"><td>
    				<a href="${ctx}/project/fsProject/form?id=${fsProject.id}">修改</a>
					<a href="${ctx}/project/fsProject/delete?id=${fsProject.id}" onclick="return confirmx('确认要删除该项目管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>