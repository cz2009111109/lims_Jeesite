<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品类别管理管理</title>
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
		<li class="active"><a href="${ctx}/good/fsGoodCategory/">商品类别管理列表</a></li>
		<shiro:hasPermission name="good:fsGoodCategory:edit"><li><a href="${ctx}/good/fsGoodCategory/form">商品类别管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsGoodCategory" action="${ctx}/good/fsGoodCategory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类别名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>说明：</label>
				<form:input path="description" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类别名称</th>
				<th>说明</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="good:fsGoodCategory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsGoodCategory">
			<tr>
				<td><a href="${ctx}/good/fsGoodCategory/form?id=${fsGoodCategory.id}">
					${fsGoodCategory.name}
				</a></td>
				<td>
					${fsGoodCategory.description}
				</td>
				<td>
					<fmt:formatDate value="${fsGoodCategory.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fsGoodCategory.remarks}
				</td>
				<shiro:hasPermission name="good:fsGoodCategory:edit"><td>
    				<a href="${ctx}/good/fsGoodCategory/form?id=${fsGoodCategory.id}">修改</a>
					<a href="${ctx}/good/fsGoodCategory/delete?id=${fsGoodCategory.id}" onclick="return confirmx('确认要删除该商品类别管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>