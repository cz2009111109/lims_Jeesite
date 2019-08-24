<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提取检测任务管理</title>
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
		<li class="active"><a href="${ctx}/task/fsExtractionDetectionTask/">提取检测任务列表</a></li>
		<shiro:hasPermission name="task:fsExtractionDetectionTask:edit"><li><a href="${ctx}/task/fsExtractionDetectionTask/form">提取检测任务添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsExtractionDetectionTask" action="${ctx}/task/fsExtractionDetectionTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>提取检测任务单号：</label>
				<form:input path="number" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户：</label>
				<form:input path="customer" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>总项目：</label>
				<form:input path="project.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>子项目：</label>
				<form:input path="subproject.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>总负责人：</label>
				<sys:treeselect id="chiefOfficer" name="chiefOfficer.id" value="${fsExtractionDetectionTask.chiefOfficer.id}" labelName="chiefOfficer.name" labelValue="${fsExtractionDetectionTask.chiefOfficer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>实验负责人：</label>
				<sys:treeselect id="labOfficer" name="labOfficer.id" value="${fsExtractionDetectionTask.labOfficer.id}" labelName="labOfficer.name" labelValue="${fsExtractionDetectionTask.labOfficer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>信息负责人：</label>
				<sys:treeselect id="infomationOfficer" name="infomationOfficer.id" value="${fsExtractionDetectionTask.infomationOfficer.id}" labelName="infomationOfficer.name" labelValue="${fsExtractionDetectionTask.infomationOfficer.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>提取检测任务单号</th>
				<th>客户</th>
				<th>总项目</th>
				<th>子项目</th>
				<th>总负责人</th>
				<th>实验负责人</th>
				<th>信息负责人</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="task:fsExtractionDetectionTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsExtractionDetectionTask">
			<tr>
				<td><a href="${ctx}/task/fsExtractionDetectionTask/form?id=${fsExtractionDetectionTask.id}">
					${fsExtractionDetectionTask.number}
				</a></td>
				<td>
					${fsExtractionDetectionTask.customer}
				</td>
				<td>
					${fsExtractionDetectionTask.project.id}
				</td>
				<td>
					${fsExtractionDetectionTask.subproject.id}
				</td>
				<td>
					${fsExtractionDetectionTask.chiefOfficer.name}
				</td>
				<td>
					${fsExtractionDetectionTask.labOfficer.name}
				</td>
				<td>
					${fsExtractionDetectionTask.infomationOfficer.name}
				</td>
				<td>
					<fmt:formatDate value="${fsExtractionDetectionTask.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fsExtractionDetectionTask.remarks}
				</td>
				<shiro:hasPermission name="task:fsExtractionDetectionTask:edit"><td>
    				<a href="${ctx}/task/fsExtractionDetectionTask/form?id=${fsExtractionDetectionTask.id}">修改</a>
					<a href="${ctx}/task/fsExtractionDetectionTask/delete?id=${fsExtractionDetectionTask.id}" onclick="return confirmx('确认要删除该提取检测任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>