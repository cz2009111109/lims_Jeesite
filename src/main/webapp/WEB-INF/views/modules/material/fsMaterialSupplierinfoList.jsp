<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商信息管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出测试数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/material/fsMaterialSupplierinfo/export");
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
		<form id="importForm" action="${ctx}/material/fsMaterialSupplierinfo/import" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/material/fsMaterialSupplierinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/material/fsMaterialSupplierinfo/">供应商信息管理列表</a></li>
		<shiro:hasPermission name="material:fsMaterialSupplierinfo:edit"><li><a href="${ctx}/material/fsMaterialSupplierinfo/form">供应商信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsMaterialSupplierinfo" action="${ctx}/material/fsMaterialSupplierinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商编号：</label>
				<form:input path="suppCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
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
				<th>供应商编号</th>
				<th>供货商名称</th>
				<th>供应商联系人</th>
				<th>手机电话</th>
				<th>地址</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="material:fsMaterialSupplierinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsMaterialSupplierinfo">
			<tr>
				<td><a href="${ctx}/material/fsMaterialSupplierinfo/form?id=${fsMaterialSupplierinfo.id}">
					${fsMaterialSupplierinfo.suppCode}
				</a></td>
				<td>
					${fsMaterialSupplierinfo.name}
				</td>
				<td>
					${fsMaterialSupplierinfo.personname}
				</td>
				<td>
					${fsMaterialSupplierinfo.mobilephonne}
				</td>
				<td>
					${fsMaterialSupplierinfo.address}
				</td>
				<td>
					${fsMaterialSupplierinfo.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fsMaterialSupplierinfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="material:fsMaterialSupplierinfo:edit"><td>
    				<a href="${ctx}/material/fsMaterialSupplierinfo/form?id=${fsMaterialSupplierinfo.id}">修改</a>
					<a href="${ctx}/material/fsMaterialSupplierinfo/delete?id=${fsMaterialSupplierinfo.id}" onclick="return confirmx('确认要删除该供应商信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>