<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试附件管理</title>
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
		<li class="active"><a href="${ctx}/test/testDataFile/">测试附件列表</a></li>
		<shiro:hasPermission name="test:testDataFile:edit"><li><a href="${ctx}/test/testDataFile/form">测试附件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testDataFile" action="${ctx}/test/testDataFile/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>操作</th>
				<th>归属用户</th>
				<th>归属部门</th>
				<th>归属区域</th>
				<th>名称</th>
				<th>性别</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="test:testDataFile:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testDataFile">
			<tr>
				<td><a href="${ctx}/test/testDataFile/form?id=${testDataFile.id}">
					查看详情
				</a></td>
				<td>
						${testDataFile.user.name}
				</td>
				<td>
					${testDataFile.office.name}
				</td>
				<td>
					${testDataFile.area.name}
				</td>
				<td>
					${testDataFile.name}
				</td>
				<td>
					${testDataFile.sex}
				</td>
				<td>
					<fmt:formatDate value="${testDataFile.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${testDataFile.remarks}
				</td>
				<shiro:hasPermission name="test:testDataFile:edit"><td>
    				<a href="${ctx}/test/testDataFile/form?id=${testDataFile.id}">修改</a>
					<a href="${ctx}/test/testDataFile/delete?id=${testDataFile.id}" onclick="return confirmx('确认要删除该测试附件吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>