<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试剂供应商管理管理</title>
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
		<li class="active"><a href="${ctx}/cooperation/fsGoodCooperation/">试剂供应商管理列表</a></li>
		<shiro:hasPermission name="cooperation:fsGoodCooperation:edit"><li><a href="${ctx}/cooperation/fsGoodCooperation/form">试剂供应商管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsGoodCooperation" action="${ctx}/cooperation/fsGoodCooperation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供货商名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>供应商联系人：</label>
				<form:input path="personname" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>手机电话：</label>
				<form:input path="mobilephonne" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>座机：</label>
				<form:input path="telephone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>邮箱：</label>
				<form:input path="email" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供货商名称</th>
				<th>银行</th>
				<th>银行账号</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="cooperation:fsGoodCooperation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsGoodCooperation">
			<tr>
				<td><a href="${ctx}/cooperation/fsGoodCooperation/form?id=${fsGoodCooperation.id}">
					${fsGoodCooperation.name}
				</a></td>
				<td><a href="${ctx}/bank/fsBank/form?id=${fsGoodCooperation.fsBank.id}">
					${fsGoodCooperation.fsBank.company}
				</a></td>
				<td>
						${fsGoodCooperation.fsBank.openBank}
				</td>
				<td>
						${fsGoodCooperation.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fsGoodCooperation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cooperation:fsGoodCooperation:edit"><td>
    				<a href="${ctx}/cooperation/fsGoodCooperation/form?id=${fsGoodCooperation.id}">修改</a>
					<a href="${ctx}/cooperation/fsGoodCooperation/delete?id=${fsGoodCooperation.id}" onclick="return confirmx('确认要删除该试剂供应商管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>