<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申购单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
            $("#btnSubmit").click(function () {
                $("select").removeAttr("disabled")
                $("#Submit").submit();
            })
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出测试数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/material/fsMaterialPurchasingorder/orderlist/export");
                        $("#searchForm").submit();
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
            $("#btnImport").click(function(){
                if($("#id").val()==""){
                    top.$.jBox.info("请先保存申购单")
				}else{
                $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"确定":"ok","关闭":true},
                    bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！",submit:function (v,h,f) {
                        if(v=="ok"){
                            var formData = new FormData();
                            formData.append("file",h.find("#uploadFile")[0].files[0]);
                            $.ajax({
                                url:'${ctx}/material/fsMaterialPurchasingorder/orderlist/import',
                                dataType:'json',
                                type:'POST',
                                async: false,
                                data: formData,
                                processData : false, // 使数据不做处理
                                contentType : false, // 不要设置Content-Type请求头
                                success: function(response){
                                    console.log(response);
                                    if(response.data.length>0){
                                        for (var i=0; i<response.data.length; i++){
                                            addRow('#fsMaterialPurchasingorderlistList', fsMaterialPurchasingorderlistRowIdx, fsMaterialPurchasingorderlistTpl, response.data[i]);
                                            fsMaterialPurchasingorderlistRowIdx = fsMaterialPurchasingorderlistRowIdx + 1;
                                        }
									}
                                },
                                error:function(response){
                                    console.log(response);
                                }
                            });


                        }
                    }})}
            });

            $("#tableSelect").click(function () {
                $.jBox("iframe:${ctx}/good/fsGood/fsGoodlist",{title:"选择数据",width:800, height:380, buttons:{"确定":"ok","关闭":true},
                    submit:function (v,h,f) {
                        if(v=="ok"){
                            var table=h.find("iframe")[0].contentWindow.contentTable;
                            var table=$(table)
                            var children=[];
                            table.find("tbody").find('input:checkbox:checked').map(function (index,ele) {
                                children.push(ele.value)
                            })
                            console.log(children)
                            $.ajax({
                                url:'${ctx}/material/fsMaterialPurchasingorder/orderlist/tableSelectOrderList',
                                dataType:'json',
                                type:'POST',
                                async: false,
                                traditional:true,
                                data: {ids:children},
                                success: function(response){
                                    console.log(response);
                                    console.log(response.data.length>0);
                                    if(response.data.length>0){

                                        $.each(response.data,function (index,item) {
											if(item.purchNum){
                                                item.purchNum = item.purchNum==""?1:item.purchNum;
											}else{
                                                item.purchNum =1;
											}
											if(!item.purchTotalprice){
                                                item.purchTotalprice=item.actualprice*item.purchNum;
											}
											if(!item.purchStatus){
                                                item.purchStatus=1;
											}
                                            if(!item.invoiceStatus){
                                                item.invoiceStatus=1;
                                            }
                                            if(!item.payStatus){
                                                item.payStatus=1;
                                            }
                                            if(!item.entryStatus){
                                                item.entryStatus=1;
                                            }
                                        });
                                        for (var i=0; i<response.data.length; i++){
                                            addRow('#fsMaterialPurchasingorderlistList', fsMaterialPurchasingorderlistRowIdx, fsMaterialPurchasingorderlistTpl, response.data[i]);
                                            fsMaterialPurchasingorderlistRowIdx = fsMaterialPurchasingorderlistRowIdx + 1;
                                        }
									}

                                },
                                error:function(response){
                                    console.log(response);
                                }
                            });
                        }

                    }, loaded:function(h){
                        console.log($(".jbox-content", top.document))
                        $(".jbox-content").css("overflow-y","hidden");
                    }})
            })
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
            $(list+idx+"_purchNum").on('input propertychange',function(){
                $(list+idx+"_purchTotalprice").val($(this).val()*$(list+idx+"_actualprice").val())
                $(list+idx+"_surplusNum").val($(this).val())
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
	<div id="importBox" class="hide">
		<form id="importForm" action="" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/material/fsMaterialPurchasingorder/orderlist/import/template">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li><a href="${ctx}/material/fsMaterialPurchasingorder/">申购单列表</a></li>
		<li class="active"><a href="${ctx}/material/fsMaterialPurchasingorder/form?id=${fsMaterialPurchasingorder.id}">申购单<shiro:hasPermission name="material:fsMaterialPurchasingorder:edit">${not empty fsMaterialPurchasingorder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="material:fsMaterialPurchasingorder:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fsMaterialPurchasingorder" action="${ctx}/material/fsMaterialPurchasingorder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">申购单编号：</label>
			<div class="controls">
				<form:input path="orderCode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请人：</label>
			<div class="controls input-xlarge">
				<%--<form:input path="orderUserid.id" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
				<sys:treeselect id="orderUserid" name="orderUserid.id" value="${fsMaterialPurchasingorder.orderUserid.id}" labelName="orderUserid.name" labelValue="${fsMaterialPurchasingorder.orderUserid.name}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请部门：</label>
			<div class="controls">
				<sys:treeselect id="orderUserdepart" name="orderUserdepart.id" value="${fsMaterialPurchasingorder.orderUserdepart.id}" labelName="orderUserdepart.name" labelValue="${fsMaterialPurchasingorder.orderUserdepart.name}"
								title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请日期：</label>
			<div class="controls">
				<input name="purchTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsMaterialPurchasingorder.purchTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">采购原因：</label>
			<div class="controls">
				<form:textarea path="purchReason" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-medium">
					<form:options  items="${fns:getDictList('purchasingorder_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购类型：</label>
			<div class="controls">
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options  items="${fns:getDictList('purchasingorder_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">期望到货日期：</label>
			<div class="controls">
				<input name="expectedTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fsMaterialPurchasingorder.expectedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">试剂耗材采购申请物品表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>物品</th>
								<th>货号</th>
								<th>物品名称</th>
								<th>品牌</th>
								<th>类别</th>
								<th>采购数量</th>
								<th>总价</th>
								<th>目录价格</th>
								<th>实际价格</th>
								<th>商品规格</th>
								<th>单位</th>
								<th>供应商</th>


								<th>货物状态</th>
								<th>发票状态</th>
								<th>付款状态</th>
								<th>备注</th>
								<th>入库状态</th>
								<th>备注信息</th>
								<shiro:hasPermission name="material:fsMaterialPurchasingorder:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="fsMaterialPurchasingorderlistList">
						</tbody>
						<shiro:hasPermission name="material:fsMaterialPurchasingorder:edit"><tfoot>
							<tr>
								<td colspan="20">
									<%--<a href="javascript:" onclick="addRow('#fsMaterialPurchasingorderlistList', fsMaterialPurchasingorderlistRowIdx, fsMaterialPurchasingorderlistTpl);fsMaterialPurchasingorderlistRowIdx = fsMaterialPurchasingorderlistRowIdx + 1;" class="btn">新增</a>--%>
										<input id="tableSelect" class="btn btn-primary" type="button" value="添加"/>
										<input id="btnImport" class="btn btn-primary" type="button" value="Excel导入"/>
										<input id="btnExport" class="btn btn-primary" type="button" value="Excel导出"/>

								</td>
							</tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="fsMaterialPurchasingorderlistTpl">//<!--
						<tr id="fsMaterialPurchasingorderlistList{{idx}}">
							<td class="hide">
								<input id="fsMaterialPurchasingorderlistList{{idx}}_id" name="fsMaterialPurchasingorderlistList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_delFlag" name="fsMaterialPurchasingorderlistList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_materialId" name="fsMaterialPurchasingorderlistList[{{idx}}].materialId.id" type="text" value="{{row.materialId.id}}" maxlength="64" class="input-small " readonly/>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_materialCode" name="fsMaterialPurchasingorderlistList[{{idx}}].materialCode" type="text" value="{{row.materialCode}}" maxlength="50" class="input-small " readonly/>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_materialName" name="fsMaterialPurchasingorderlistList[{{idx}}].materialName" type="text" value="{{row.materialName}}" maxlength="150" class="input-small " readonly/>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_brand" name="fsMaterialPurchasingorderlistList[{{idx}}].brand" type="text" value="{{row.brand}}" maxlength="255" class="input-small " readonly/>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_category" name="fsMaterialPurchasingorderlistList[{{idx}}].category" type="text" value="{{row.category}}" maxlength="64" class="input-small " readonly/>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_purchNum" name="fsMaterialPurchasingorderlistList[{{idx}}].purchNum" type="number" value="{{row.purchNum}}" min="1" class="input-small  digits" />
							</td>
							<td class="hide">
								<input id="fsMaterialPurchasingorderlistList{{idx}}_surplusNum" name="fsMaterialPurchasingorderlistList[{{idx}}].surplusNum" type="number" value="{{row.surplusNum}}" min="1" class="input-small  digits" />
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_purchTotalprice" name="fsMaterialPurchasingorderlistList[{{idx}}].purchTotalprice" type="text" value="{{row.purchTotalprice}}" class="input-small " readonly/>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_catalogprice" name="fsMaterialPurchasingorderlistList[{{idx}}].catalogprice" type="text" value="{{row.catalogprice}}" class="input-small " readonly/>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_actualprice" name="fsMaterialPurchasingorderlistList[{{idx}}].actualprice" type="text" value="{{row.actualprice}}" class="input-small " readonly/>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_unitnum" name="fsMaterialPurchasingorderlistList[{{idx}}].unitnum" type="text" value="{{row.unitnum}}" maxlength="20" class="input-small  digits" readonly/>
							</td>
							<td>
								<select id="fsMaterialPurchasingorderlistList{{idx}}_unit" name="fsMaterialPurchasingorderlistList[{{idx}}].unit" data-value="{{row.unit}}" class="input-small " disabled="disabled">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('material_unit')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_supplierinfo" name="fsMaterialPurchasingorderlistList[{{idx}}].supplierinfo.id" type="text" value="{{row.supplierinfo.id}}" maxlength="64" class="input-small " readonly/>
							</td>

							<td>
								<select id="fsMaterialPurchasingorderlistList{{idx}}_purchStatus" name="fsMaterialPurchasingorderlistList[{{idx}}].purchStatus" data-value="{{row.purchStatus}}" class="input-small " disabled="disabled">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('purchStatus')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select id="fsMaterialPurchasingorderlistList{{idx}}_invoiceStatus" name="fsMaterialPurchasingorderlistList[{{idx}}].invoiceStatus" data-value="{{row.invoiceStatus}}" class="input-small "  disabled="disabled">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('invoiceStatus')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select id="fsMaterialPurchasingorderlistList{{idx}}_payStatus" name="fsMaterialPurchasingorderlistList[{{idx}}].payStatus" data-value="{{row.payStatus}}" class="input-small "  disabled="disabled">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('payStatus')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="fsMaterialPurchasingorderlistList{{idx}}_description" name="fsMaterialPurchasingorderlistList[{{idx}}].description" type="text" value="{{row.description}}" class="input-small " readonly/>
							</td>
							<td>
								<select id="fsMaterialPurchasingorderlistList{{idx}}_entryStatus" name="fsMaterialPurchasingorderlistList[{{idx}}].entryStatus" data-value="{{row.entryStatus}}" class="input-small " disabled="disabled">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('entryStatus')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<textarea id="fsMaterialPurchasingorderlistList{{idx}}_remarks" name="fsMaterialPurchasingorderlistList[{{idx}}].remarks" rows="1" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="material:fsMaterialPurchasingorder:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#fsMaterialPurchasingorderlistList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var fsMaterialPurchasingorderlistRowIdx = 0, fsMaterialPurchasingorderlistTpl = $("#fsMaterialPurchasingorderlistTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(fsMaterialPurchasingorder.fsMaterialPurchasingorderlistList)};
							for (var i=0; i<data.length; i++){
								addRow('#fsMaterialPurchasingorderlistList', fsMaterialPurchasingorderlistRowIdx, fsMaterialPurchasingorderlistTpl, data[i]);
								fsMaterialPurchasingorderlistRowIdx = fsMaterialPurchasingorderlistRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="material:fsMaterialPurchasingorder:edit">
                <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
                <input id="Submit" class="hide" type="submit" value="保 存"/>&nbsp;
            </shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>