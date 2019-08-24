<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提取检测报告管理</title>
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
		<li class="active"><a href="${ctx}/reprot/fsExtractionDetectionReport/">提取检测报告列表</a></li>
		<shiro:hasPermission name="reprot:fsExtractionDetectionReport:edit"><li><a href="${ctx}/reprot/fsExtractionDetectionReport/form">提取检测报告添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsExtractionDetectionReport" action="${ctx}/reprot/fsExtractionDetectionReport/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>客户</th>
				<th>总负责人</th>
				<th>实验负责人</th>
				<th>信息负责人</th>
				<th>收样人</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="reprot:fsExtractionDetectionReport:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsExtractionDetectionReport">
			<tr>
				<td><a href="${ctx}/reprot/fsExtractionDetectionReport/form?id=${fsExtractionDetectionReport.id}">
					${fsExtractionDetectionReport.customer}
				</a></td>
				<td>
					${fsExtractionDetectionReport.chiefOfficer.name}
				</td>
				<td>
					${fsExtractionDetectionReport.labOfficer.name}
				</td>
				<td>
					${fsExtractionDetectionReport.infomationOfficer.name}
				</td>
				<td>
					${fsExtractionDetectionReport.receiveUser.name}
				</td>
				<td>
					<fmt:formatDate value="${fsExtractionDetectionReport.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fsExtractionDetectionReport.remarks}
				</td>
				<shiro:hasPermission name="reprot:fsExtractionDetectionReport:edit"><td>
    				<a href="${ctx}/reprot/fsExtractionDetectionReport/form?id=${fsExtractionDetectionReport.id}">修改</a>
					<a href="${ctx}/reprot/fsExtractionDetectionReport/delete?id=${fsExtractionDetectionReport.id}" onclick="return confirmx('确认要删除该提取检测报告吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>