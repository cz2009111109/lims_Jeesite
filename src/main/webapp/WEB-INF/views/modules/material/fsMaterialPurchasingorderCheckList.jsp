<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>记录保存成功管理</title>
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
		<li class="active"><a href="${ctx}/material/fsMaterialPurchasingorderCheck/">记录保存成功列表</a></li>
		<shiro:hasPermission name="material:fsMaterialPurchasingorderCheck:edit"><li><a href="${ctx}/material/fsMaterialPurchasingorderCheck/form">记录保存成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsMaterialPurchasingorderCheck" action="${ctx}/material/fsMaterialPurchasingorderCheck/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申购货物：</label>
				<form:input path="purchasingorder.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>提交人：</label>
				<sys:treeselect id="submitUser" name="submitUser.id" value="${fsMaterialPurchasingorderCheck.submitUser.id}" labelName="user.name" labelValue="${fsMaterialPurchasingorderCheck.submitUser.name}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>审核人：</label>
				<sys:treeselect id="checkUser" name="checkUser.id" value="${fsMaterialPurchasingorderCheck.checkUser.id}" labelName="user.name" labelValue="${fsMaterialPurchasingorderCheck.checkUser.name}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="input-medium" allowClear="true" notAllowSelectParent="true"/>
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
				<th>申购货物</th>
				<th>提交人</th>
				<th>审核人</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="material:fsMaterialPurchasingorderCheck:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsMaterialPurchasingorderCheck">
			<tr>
				<td><a href="${ctx}/material/fsMaterialPurchasingorderCheck/form?id=${fsMaterialPurchasingorderCheck.id}">
					${fsMaterialPurchasingorderCheck.purchasingorder.orderCode}
				</a></td>
				<td><a href="${ctx}/sys/user/form?id=${fsMaterialPurchasingorderCheck.submitUser.id}">
					${fsMaterialPurchasingorderCheck.submitUser.name}

				</a></td>
				<td><a href="${ctx}/sys/user/form?id=${fsMaterialPurchasingorderCheck.checkUser.id}">
						${fsMaterialPurchasingorderCheck.checkUser.name}
				</a></td>
				<td>
					<c:if test="${not empty fsMaterialPurchasingorderCheck.remarks}">
						<c:set value="${fn:split(fsMaterialPurchasingorderCheck.remarks,';')}" var="remarks"></c:set>
						<c:forEach var="item" items="${remarks}" >
							<c:if test="${not empty item}">
								<c:out value="${item}"></c:out><br/>
							</c:if>
						</c:forEach>
					</c:if>


				</td>
				<td>
					<fmt:formatDate value="${fsMaterialPurchasingorderCheck.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="material:fsMaterialPurchasingorderCheck:edit"><td>
    				<a href="${ctx}/material/fsMaterialPurchasingorderCheck/form?id=${fsMaterialPurchasingorderCheck.id}">修改</a>
					<a href="${ctx}/material/fsMaterialPurchasingorderCheck/delete?id=${fsMaterialPurchasingorderCheck.id}" onclick="return confirmx('确认要删除该记录保存成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>