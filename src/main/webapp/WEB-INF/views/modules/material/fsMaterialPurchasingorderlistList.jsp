<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申购单货物管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            var objectMap={};
            var fsMaterialPurchasingorderlistRowIdx = 0, fsMaterialPurchasingorderlistTpl = $("#fsMaterialPurchasingorderlistTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
            objectMap=${fns:toJson(page.list)};
            $("input[id='all']").change(function(){
                $("#contentTable tbody td").find("input[type=checkbox]").each(function(){
                    $(this).attr("checked", !$(this).attr("checked"));
                });
            });
			$("#objectIn").click(function () {
                var checkOptions=[];
                var checkObjects=[];
                $("#tbodySelect").html("");
                fsMaterialPurchasingorderlistRowIdx=0;
                $("#contentTable tbody td").find("input[type=checkbox]:checked").each(function (index) {

                    if(objectMap[index].entryStatus<2){
                        checkOptions.push($(this).val());
                        checkObjects.push(objectMap[index]);
					}

                })
				for(var i=0;i<checkObjects.length;i++){
                    addRow('#tbodySelect', fsMaterialPurchasingorderlistRowIdx, fsMaterialPurchasingorderlistTpl, checkObjects[i]);
                    fsMaterialPurchasingorderlistRowIdx = fsMaterialPurchasingorderlistRowIdx + 1;
				}
                $.jBox("html:"+$("#tableSelect").html(), {title:"选择", buttons:{"确定":"ok","关闭":true}, width: 1000, height: 500,showScrolling: true,
					loaded:function (h) {
                        h.find("#tbodySelect").find("tr").each(function (index,value) {
                            $(this).find("input[name='objectIn']").on("input propertychange",function () {
								if($(this).val()<0){
                                    $(this).val(0)
								}
                                if(parseFloat($(this).val())>parseFloat($(this).parent().parent().find("input[name='surplusNum']").val())){
                                    $(this).val($(this).parent().parent().find("input[name='surplusNum']").val())
								}
                            })
                        })

                        h.find("#warehouseinfoButton").click(function(){
                            // 是否限制选择，如果限制，设置为disabled
                            if ($(this).hasClass("disabled")){
                                return true;
                            }
                            var _this=$(this)
                            console.log($(this))
                            console.log($(this).prev().prev().val())
                            // 正常打开
                            top.$.jBox.open("iframe:/admin/tag/treeselect?url="+encodeURIComponent("/material/fsMaterialWarehouseinfo/treeData")+"&module=&checked=&extId="+$(this).prev().prev().val()+"&isAll=&isShowAll=true", "选择存储位置", 400, 420, {
                                ajaxData:{selectIds: $(this).prev().prev().val()},buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v1, h1, f1){
                                    console.log($(this))
                                    if (v1=="ok"){
                                        var tree = h1.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
                                        var ids = [], names = [], nodes = [];
                                        if ("" == "true"){
                                            nodes = tree.getCheckedNodes(true);
                                        }else{
                                            nodes = tree.getSelectedNodes();
                                        }
                                        for(var i=0; i<nodes.length; i++) {//
                                            ids.push(nodes[i].id);
                                            names.push(nodes[i].name);//
                                            break;
                                            //  // 如果为非复选框选择，则返回第一个选择
                                        }
                                        _this.parent().find("#warehouseinfoId").val(ids.join(",").replace(/u_/ig,""));
                                        _this.parent().find("#warehouseinfoName").val(names.join(","));
                                    }
                                    //
                                    else if (v1=="clear"){
                                        _this.parent().find("#warehouseinfoId").val("");
                                        _this.parent().find("#warehouseinfoName").val("");
                                    }
                                    //
                                    if(typeof warehouseinfoTreeselectCallBack == 'function'){
                                        warehouseinfoTreeselectCallBack(v1, h1, f1);
                                    }
                                }, loaded:function(h1){
                                    h1.find(".jbox-content", top.document).css("overflow-y","hidden");
                                    console.log(h1)
                                }
                            });
                        });

                    },
					submit:function (v,h,f) {

                        if(v=="ok"){
                            h.find("#tbodySelect").find("tr").each(function (index,value) {
                                checkObjects[index].objectIn=$(this).find("input[name='objectIn']").val();
                                checkObjects[index].warehouseinfo={};
                                checkObjects[index].warehouseinfo.id=$(this).find("input[name='warehouseinfoId']").val();
                                checkObjects[index].warehouseinfo.name=$(this).find("input[name='warehouseinfoName']").val();
								console.log(checkObjects[index])
                            })
                            $.ajax({
                                url:'${ctx}/material/fsMaterialPurchasingorderlist/objectIn',
                                dataType:'json',
                                type:'POST',
                                contentType:'application/json;charset=UTF-8',// 核心
                                async: false,
                                traditional:true,
                                data: JSON.stringify(checkObjects),
                                success: function(response){
                                    console.log(response)
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
                                    console.log(response)
                                    $.jBox.error(response.msg,"信息提示",{submit:function (v,h,f) {
                                           window.location.reload();
                                        }});

                                }
                            });
                        }
                    },
                    closed:function(){

					}
                })
            })
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function addRow(list, idx, tpl, row){
            $(list).append(Mustache.render(tpl, {
                idx: idx, delBtn: true, row: row
            }));
            $("#fsMaterialPurchasingorderlistList"+idx).find("select").each(function(){
                $(this).find("option[value="+$(this).attr("data-value")+"]").attr("selected",true);
            });
            $("#fsMaterialPurchasingorderlistList"+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
                var ss = $(this).attr("data-value").split(',');
                for (var i=0; i<ss.length; i++){
                    if($(this).val() == ss[i]){
                        $(this).attr("checked","checked");
                    }
                }
            });

        }
        function delRow(obj, prefix){
            var id = $(prefix+"_id");
            var delFlag = $(prefix+"_delFlag");
            if (id.val() == ""){
                $(obj).parent().parent().remove();
            }else if(delFlag.val() == "0"){
                delFlag.val("1");
                $(obj).html("&divide;").attr("title", "撤销删除");
                $(obj).parent().parent().addClass("error");
            }else if(delFlag.val() == "1"){
                delFlag.val("0");
                $(obj).html("&times;").attr("title", "删除");
                $(obj).parent().parent().removeClass("error");
            }
        }
	</script>
</head>
<body>
	<div id="tableSelect" class="hide">
		<table id="contentTableSelect" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>申购单号</th>
					<th>货号</th>
					<th>物品名称</th>
					<th>待入库数量</th>

					<th>入库数量</th>
					<th>仓库位置</th>
					<th>货物状态</th>
					<th>规格</th>
					<th>单位</th>

				</tr>
			</thead>
			<tbody id="tbodySelect">

			</tbody>
		</table>
	</div>

	<script type="text/template" id="fsMaterialPurchasingorderlistTpl">//<!--
		<tr id="fsMaterialPurchasingorderlistList{{idx}}">
			<td class="hide">
				<input id="fsMaterialPurchasingorderlistList{{idx}}_id" name="id" type="hidden" value="{{row.id}}"/>
				<input id="fsMaterialPurchasingorderlistList{{idx}}_delFlag" name="delFlag" type="hidden" value="0"/>
			</td>
			<td>
				<input id="fsMaterialPurchasingorderlistList{{idx}}_orderId" name="orderId.orderCode" type="text" value="{{row.orderId.orderCode}}" style="width:160px;" maxlength="64" class="input-small " readonly/>
			</td>
			<td>
				<input id="fsMaterialPurchasingorderlistList{{idx}}_materialCode" name="materialCode" type="text" value="{{row.materialCode}}" maxlength="50" class="input-small " readonly/>
			</td>
			<td>
				<input id="fsMaterialPurchasingorderlistList{{idx}}_materialName" name="materialName" type="text" value="{{row.materialName}}" maxlength="150" class="input-small " readonly/>
			</td>
			<td>
				<input id="fsMaterialPurchasingorderlistList{{idx}}_surplusNum" name="surplusNum" type="number" value="{{row.surplusNum}}" min="1" style="width:50px;" class="input-small  " readonly />
			</td>
			<td class="hide">
				<input id="fsMaterialPurchasingorderlistList{{idx}}_purchNum" name="purchNum" type="number" value="{{row.purchNum}}" min="1" style="width:50px;" class="input-small " />
			</td>

			<td>
				<input id="fsMaterialPurchasingorderlistList{{idx}}_objectIn" name="objectIn" type="number" value="{{row.surplusNum}}" maxlength="20" min="0"  max="{{row.surplusNum}}" style="width:50px;" class="input-small "/>
			</td>
			<td>
				<div class="input-append">
					<input id="warehouseinfoId" name="warehouseinfoId" class="" type="hidden" value="{{row.warehouseinfo}}"/>
					<input id="warehouseinfoName" name="warehouseinfoName" readonly="readonly" type="text" value="{{row.warehouseinfo.name}}" data-msg-required=""
						class="input-small" style="width:80px;"/><a id="warehouseinfoButton" href="javascript:" class="btn  " style="">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
				</div>
			</td>
			<td>
				<select id="fsMaterialPurchasingorderlistList{{idx}}_entryStatus" name="entryStatus" data-value="{{row.entryStatus}}" style="width:80px;" class="input-small " readonly>
					<option value=""></option>
					<c:forEach items="${fns:getDictList('entryStatus')}" var="dict">
						<option value="${dict.value}">${dict.label}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<input id="fsMaterialPurchasingorderlistList{{idx}}_unitnum" name="unitnum" type="text" value="{{row.unitnum}}" maxlength="20" style="width:50px;" class="input-small " readonly />
			</td>
			<td>
				<select id="fsMaterialPurchasingorderlistList{{idx}}_unit" name="unit" data-value="{{row.unit}}" style="width:50px;" class="input-small " disabled="disabled">
					<option value=""></option>
					<c:forEach items="${fns:getDictList('material_unit')}" var="dict">
						<option value="${dict.value}">${dict.label}</option>
					</c:forEach>
				</select>
			</td>
		</tr>//-->

	</script>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/material/fsMaterialPurchasingorderlist/">申购单货物列表</a></li>
		<%--<shiro:hasPermission name="material:fsMaterialPurchasingorderlist:edit"><li><a href="${ctx}/material/fsMaterialPurchasingorderlist/form">申购单货物添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="fsMaterialPurchasingorderlist" action="${ctx}/material/fsMaterialPurchasingorderlist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>物品：</label>
				<form:input path="materialId.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>货号：</label>
				<form:input path="materialCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>物品名称：</label>
				<form:input path="materialName" htmlEscape="false" maxlength="150" class="input-medium"/>
			</li>
			<li><label>品牌：</label>
				<form:input path="brand" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>货物状态：</label>
				<form:select path="purchStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('purchStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>发票状态：</label>
				<form:select path="invoiceStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('invoiceStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>付款状态：</label>
				<form:select path="payStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('payStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>入库状态：</label>
				<form:select path="entryStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('entryStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="material:fsMaterialPurchasingorderlist:edit">
			<li class="btns"><input id="objectIn" class="btn btn-primary" type="button" value="入库"/></li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 80px;"><input id="all" type="checkbox" name="" />全选</th>
				<th>申购单</th>
				<th>货号</th>
				<th>物品名称</th>
				<th>待入库数量</th>
				<th>规格</th>
				<th>单位</th>
				<th>品牌</th>
				<th>货物状态</th>
				<th>发票状态</th>
				<th>付款状态</th>
				<th>入库状态</th>
				<th>创建时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="material:fsMaterialPurchasingorderlist:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fsMaterialPurchasingorderlist">
			<tr>
				<td><input type="checkbox" name="id" value="${fsMaterialPurchasingorderlist.id}" /></td>
				<td><a href="${ctx}/material/fsMaterialPurchasingorderlist/form?id=${fsMaterialPurchasingorderlist.id}">
					${fsMaterialPurchasingorderlist.orderId.orderCode}
				</a></td>
				<td>
					${fsMaterialPurchasingorderlist.materialCode}
				</td>
				<td>
					${fsMaterialPurchasingorderlist.materialName}
				</td>
				<td>
					${fsMaterialPurchasingorderlist.surplusNum}
				</td>
				<td>
					${fsMaterialPurchasingorderlist.unitnum}
				</td>
				<td>
					${fsMaterialPurchasingorderlist.unit}
				</td>
				<td>
					${fsMaterialPurchasingorderlist.brand}
				</td>
				<td>
					${fns:getDictLabel(fsMaterialPurchasingorderlist.purchStatus, 'purchStatus', '')}
				</td>
				<td>
					${fns:getDictLabel(fsMaterialPurchasingorderlist.invoiceStatus, 'invoiceStatus', '')}
				</td>
				<td>
					${fns:getDictLabel(fsMaterialPurchasingorderlist.payStatus, 'payStatus', '')}
				</td>
				<td>
					${fns:getDictLabel(fsMaterialPurchasingorderlist.entryStatus, 'entryStatus', '')}
				</td>
				<td>
					<fmt:formatDate value="${fsMaterialPurchasingorderlist.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fsMaterialPurchasingorderlist.remarks}
				</td>
				<shiro:hasPermission name="material:fsMaterialPurchasingorderlist:edit"><td>
    				<a href="${ctx}/material/fsMaterialPurchasingorderlist/form?id=${fsMaterialPurchasingorderlist.id}">修改</a>
					<a href="${ctx}/material/fsMaterialPurchasingorderlist/delete?id=${fsMaterialPurchasingorderlist.id}" onclick="return confirmx('确认要删除该申购单货物吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>