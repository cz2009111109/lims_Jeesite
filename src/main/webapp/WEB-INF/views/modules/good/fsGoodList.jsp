<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出测试数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/good/fsGood/export");
                        $("#searchForm").submit();
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
            $("#btnImport").click(function(){
                $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
                    bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
            });
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
    <div id="importBox" class="hide">
        <form id="importForm" action="${ctx}/good/fsGood/import" method="post" enctype="multipart/form-data"
              class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
            <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
            <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
            <a href="${ctx}/good/fsGood/import/template">下载模板</a>
        </form>
    </div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/good/fsGood/">商品列表列表</a></li>
		<shiro:hasPermission name="good:fsGood:edit"><li><a href="${ctx}/good/fsGood/form">商品列表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsGood" action="${ctx}/good/fsGood/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>品牌：</label>
				<form:input path="brand" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>货号：</label>
				<form:input path="itemnumber" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>商品规格：</label>
				<form:input path="unitnum" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>单位：</label>
				<form:input path="unit" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>出厂厂家：</label>
				<form:input path="factory" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>功能用途：</label>
				<form:input path="function" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li><label>说明：</label>
				<form:input path="description" htmlEscape="false" maxlength="300" class="input-medium"/>
			</li>
			<li class="btns">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
                <input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
            </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品名称</th>
				<th>品牌</th>
				<th>货号</th>
				<th>规格</th>
				<th>单位</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="good:fsGood:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsGood">
			<tr>
				<td><a href="${ctx}/good/fsGood/form?id=${fsGood.id}">
					${fsGood.name}
				</a></td>
				<td>
					${fsGood.brand}
				</td>
				<td>
					${fsGood.itemnumber}
				</td>
				<td>
					${fsGood.unitnum}
				</td>
				<td>
					${fsGood.unit}
				</td>
				<td>
					<fmt:formatDate value="${fsGood.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fsGood.remarks}
				</td>
				<shiro:hasPermission name="good:fsGood:edit"><td>
    				<a href="${ctx}/good/fsGood/form?id=${fsGood.id}">修改</a>
					<a href="${ctx}/good/fsGood/delete?id=${fsGood.id}" onclick="return confirmx('确认要删除该商品列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>