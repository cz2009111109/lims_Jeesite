<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申购单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		    var objectMap={};
            objectMap=${fns:toJson(page.list)};

            $("input[id='all']").change(function(){
                $("#contentTable tbody td").find("input[type=checkbox]").each(function(){
                    $(this).attr("checked", !$(this).attr("checked"));
                });
            });
            $("#tijiao").click(function(){
                var checkOptions=[];
                $("#contentTable tbody td").find("input[type=checkbox]:checked").each(function (index) {
                    checkOptions.push($(this).val());
                })
                if(checkOptions.length>0){
                    $.ajax({
                        url:'${ctx}/material/fsMaterialPurchasingorder/tijiao',
                        dataType:'json',
                        type:'POST',
                        async: false,
                        traditional:true,
                        data: {ids:checkOptions},
                        success: function(response){
                            if(response.ret==true){
                                $.jBox.success(response.msg,"信息提示", {submit:function (v,h,f) {
                                        window.location.reload();
                                    }});
                            }else {
                                $.jBox.error(response.msg,"信息提示",{submit:function (v,h,f) {
                                        window.location.reload();
                                    }});
                            }

                        },
                        error:function(response){
                            $.jBox.error(response.msg,"信息提示",{submit:function (v,h,f) {
                                    window.location.reload();
                                }});

                        }
                    });
                }
            })

            $("#tijiaoback").click(function(){
                var checkOptions=[];
                $("#contentTable tbody td").find("input[type=checkbox]:checked").each(function (index) {
                    checkOptions.push($(this).val());
                })
                if(checkOptions.length>0){
                    $.ajax({
                        url:'${ctx}/material/fsMaterialPurchasingorder/tijiaoback',
                        dataType:'json',
                        type:'POST',
                        async: false,
                        traditional:true,
                        data: {ids:checkOptions},
                        success: function(response){
                            if(response.ret==true){
                                $.jBox.success(response.msg,"信息提示", {submit:function (v,h,f) {
                                        window.location.reload();
                                    }});
                            }else {
                                $.jBox.error(response.msg,"信息提示",{submit:function (v,h,f) {
                                        window.location.reload();
                                    }});
                            }

                        },
                        error:function(response){
                            $.jBox.error(response.msg,"信息提示",{submit:function (v,h,f) {
                                    window.location.reload();
                                }});

                        }
                    });
                }
            })

            $("#check").click(function(){
                var checkOptions=[];
                $("#contentTable tbody td").find("input[type=checkbox]:checked").each(function (index) {
                    checkOptions.push($(this).val());
                })
                if(checkOptions.length>0){
                    $.ajax({
                        url:'${ctx}/material/fsMaterialPurchasingorder/check',
                        dataType:'json',
                        type:'POST',
                        async: false,
                        traditional:true,
                        data: {ids:checkOptions},
                        success: function(response){
                            if(response.ret==true){
                                $.jBox.success(response.msg,"信息提示", {submit:function (v,h,f) {
                                    window.location.reload();
                                }});
                            }else {
                                $.jBox.error(response.msg,"信息提示",{submit:function (v,h,f) {
									window.location.reload();
								}});
							}

                        },
                        error:function(response){
                            $.jBox.error(response.msg,"信息提示",{submit:function (v,h,f) {
                                window.location.reload();
                            }});
                        }
                    });
                }
            })
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
		<li class="active"><a href="${ctx}/material/fsMaterialPurchasingorder/">申购单列表</a></li>
		<shiro:hasPermission name="material:fsMaterialPurchasingorder:edit"><li><a href="${ctx}/material/fsMaterialPurchasingorder/form">申购单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fsMaterialPurchasingorder" action="${ctx}/material/fsMaterialPurchasingorder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申购单编号：</label>
				<form:input path="orderCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>申请人：</label>
				<sys:treeselect id="orderUserid" name="orderUserid.id" value="${fsMaterialPurchasingorder.orderUserid.id}" labelName="orderUserid.name" labelValue="${fsMaterialPurchasingorder.orderUserid.name}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>申请部门：</label>
				<sys:treeselect id="orderUserdepart" name="orderUserdepart.id" value="${fsMaterialPurchasingorder.orderUserdepart.id}" labelName="orderUserdepart.name" labelValue="${fsMaterialPurchasingorder.orderUserdepart.name}"
								title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>采购原因：</label>
				<form:input path="purchReason" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>备注信息：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="tijiao" class="btn btn-primary" type="button" value="提交申请"/></li>
			<li class="btns"><input id="tijiaoback" class="btn btn-primary" type="button" value="驳回申请"/></li>
			<li class="btns"><input id="check" class="btn btn-primary" type="button" value="审核"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 80px;"><input id="all" type="checkbox" name="" />全选</th>
				<th>申购单编号</th>
				<th>申请人</th>
				<th>申请部门</th>
				<th>采购原因</th>
				<th>状态</th>
				<th>备注信息</th>
				<th>创建日期</th>
				<th>到货日期</th>
				<shiro:hasPermission name="material:fsMaterialPurchasingorder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsMaterialPurchasingorder">
			<tr>
				<td><input type="checkbox" name="id" value="${fsMaterialPurchasingorder.id}" /></td>
				<td><a href="${ctx}/material/fsMaterialPurchasingorder/form?id=${fsMaterialPurchasingorder.id}">
					${fsMaterialPurchasingorder.orderCode}
				</a></td>
				<td>
					${fsMaterialPurchasingorder.orderUserid.name}
				</td>
				<td>
					${fsMaterialPurchasingorder.orderUserdepart.name}
				</td>
				<td>
					${fsMaterialPurchasingorder.purchReason}
				</td>
				<td>
					${fns:getDictLabel(fsMaterialPurchasingorder.status, 'purchasingorder_status', '')}
				</td>
				<td>
					${fsMaterialPurchasingorder.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fsMaterialPurchasingorder.purchTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${fsMaterialPurchasingorder.expectedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="material:fsMaterialPurchasingorder:edit"><td>
    				<a href="${ctx}/material/fsMaterialPurchasingorder/form?id=${fsMaterialPurchasingorder.id}">修改</a>
					<a href="${ctx}/material/fsMaterialPurchasingorder/delete?id=${fsMaterialPurchasingorder.id}" onclick="return confirmx('确认要删除该申购单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>