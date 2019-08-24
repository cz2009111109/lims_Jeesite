<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>子项目管理管理</title>
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
		<li class="active"><a href="${ctx}/project/fsSubproject/">子项目管理列表</a></li>
		<shiro:hasPermission name="project:fsSubproject:edit"><li><a href="${ctx}/project/fsSubproject/form">子项目管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsSubproject" action="${ctx}/project/fsSubproject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>子项目编号：</label>
				<form:input path="subprojectNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>子项目名称：</label>
				<form:input path="subprojectName" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>总项目：</label>
				<form:input path="fsProject.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>销售id：</label>
				<form:input path="salesperson" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>测序平台：</label>
				<form:select path="platform" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('platform')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>子项目编号</th>
				<th>子项目名称</th>
				<th>总项目</th>
				<th>销售id</th>
				<th>测序平台</th>
				<th>备注信息</th>
				<th>update_date</th>
				<shiro:hasPermission name="project:fsSubproject:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsSubproject">
			<tr>
				<td><a href="${ctx}/project/fsSubproject/form?id=${fsSubproject.id}">
					${fsSubproject.subprojectNumber}
				</a></td>
				<td>
					${fsSubproject.subprojectName}
				</td>
				<td><a href="${ctx}/project/fsProject/form?id=${fsSubproject.fsProject.id}">
					${fsSubproject.fsProject.projectNumber}
				</a></td>
				<td>
					${fsSubproject.salesperson}
				</td>
				<td>
					${fns:getDictLabel(fsSubproject.platform, 'platform', '')}
				</td>
				<td>
					${fsSubproject.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fsSubproject.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="project:fsSubproject:edit"><td>
    				<a href="${ctx}/project/fsSubproject/form?id=${fsSubproject.id}">修改</a>
					<a href="${ctx}/project/fsSubproject/delete?id=${fsSubproject.id}" onclick="return confirmx('确认要删除该子项目管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>