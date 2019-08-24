<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>开票银行账号管理</title>
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
		<li class="active"><a href="${ctx}/bank/fsBank/">开票银行账号列表</a></li>
		<shiro:hasPermission name="bank:fsBank:edit"><li><a href="${ctx}/bank/fsBank/form">开票银行账号添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsBank" action="${ctx}/bank/fsBank/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>银行账号：</label>
				<form:input path="bankaccount" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>银行：</label>
				<form:input path="company" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>发票抬头：</label>
				<form:input path="openBank" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>创建者：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>银行账号</th>
				<th>银行</th>
				<th>发票抬头</th>
				<th>创建者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="bank:fsBank:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsBank">
			<tr>
				<td><a href="${ctx}/bank/fsBank/form?id=${fsBank.id}">
					${fsBank.bankaccount}
				</a></td>
				<td>
					${fsBank.company}
				</td>
				<td>
					${fsBank.openBank}
				</td>
				<td>
					${fsBank.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${fsBank.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fsBank.remarks}
				</td>
				<shiro:hasPermission name="bank:fsBank:edit"><td>
    				<a href="${ctx}/bank/fsBank/form?id=${fsBank.id}">修改</a>
					<a href="${ctx}/bank/fsBank/delete?id=${fsBank.id}" onclick="return confirmx('确认要删除该开票银行账号吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>