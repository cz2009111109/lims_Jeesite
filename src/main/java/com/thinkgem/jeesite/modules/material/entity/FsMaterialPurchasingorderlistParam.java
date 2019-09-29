/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.good.entity.FsGood;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * 申购单Entity
 * @author chenzhe
 * @version 2019-08-28
 */
public class FsMaterialPurchasingorderlistParam extends DataEntity<FsMaterialPurchasingorderlistParam> {

	private static final long serialVersionUID = 1L;
	private FsMaterialPurchasingorder orderId;		// 申购单 父类
	private FsGood materialId;		// 物品
	private String materialCode;		// 货号
	private String materialName;		// 物品名称
	private String brand;		// 品牌
	private String category;		// 类别
	private String catalogprice;		// 目录价格
	private String actualprice;		// 实际价格
	private Long unitnum;		// 商品规格
	private String unit;		// 单位
	private FsMaterialSupplierinfo supplierinfo;		// 供应商
	private Integer purchNum;		// 采购数量
	private String purchTotalprice;		// 总价
	private String purchStatus;		// 货物状态
	private String invoiceStatus;		// 发票状态
	private String payStatus;		// 付款状态
	private String description;		// 备注
	private String entryStatus;		// 入库状态
	private BigDecimal objectIn;		// 入库数量
	private BigDecimal surplusNum;		// 待入库数量
	private FsMaterialWarehouseinfo warehouseinfo; //仓库位置

	public FsMaterialWarehouseinfo getWarehouseinfo() {
		return warehouseinfo;
	}

	public void setWarehouseinfo(FsMaterialWarehouseinfo warehouseinfo) {
		this.warehouseinfo = warehouseinfo;
	}

	public FsMaterialPurchasingorderlistParam() {
		super();
	}

	public FsMaterialPurchasingorderlistParam(String id){
		super(id);
	}

	public FsMaterialPurchasingorderlistParam(FsMaterialPurchasingorder orderId){
		this.orderId = orderId;
	}


	public BigDecimal getObjectIn() {
		return objectIn;
	}

	public void setObjectIn(BigDecimal objectIn) {
		this.objectIn = objectIn;
	}

	public FsMaterialPurchasingorder getOrderId() {
		return orderId;
	}

	public void setOrderId(FsMaterialPurchasingorder orderId) {
		this.orderId = orderId;
	}


	public FsGood getMaterialId() {
		return materialId;
	}

	public void setMaterialId(FsGood materialId) {
		this.materialId = materialId;
	}
	
	@Length(min=0, max=50, message="货号长度必须介于 0 和 50 之间")
	@ExcelField(title="货号", align=2, sort=20)
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	@Length(min=0, max=150, message="物品名称长度必须介于 0 和 150 之间")
	@ExcelField(title="物品名称", align=2, sort=30)
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	@Length(min=0, max=255, message="品牌长度必须介于 0 和 255 之间")
	@ExcelField(title="品牌", align=2, sort=40)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=64, message="类别长度必须介于 0 和 64 之间")
	@ExcelField(title="类别", align=2, sort=50, dictType="good_category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@ExcelField(title="目录价", align=2, sort=70)
	public String getCatalogprice() {
		return catalogprice;
	}

	public void setCatalogprice(String catalogprice) {
		this.catalogprice = catalogprice;
	}

	@ExcelField(title="实际价格", align=2, sort=60)
	public String getActualprice() {
		return actualprice;
	}

	public void setActualprice(String actualprice) {
		this.actualprice = actualprice;
	}

	@ExcelField(title="规格", align=2, sort=80)
	public Long getUnitnum() {
		return unitnum;
	}

	public void setUnitnum(Long unitnum) {
		this.unitnum = unitnum;
	}
	
	@Length(min=0, max=10, message="单位长度必须介于 0 和 10 之间")
	@ExcelField(title="单位", align=2, sort=90, dictType="material_unit")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@ExcelField(title="供应商", align=2, sort=100)
	public FsMaterialSupplierinfo getSupplierinfo() {
		return supplierinfo;
	}

	public void setSupplierinfo(FsMaterialSupplierinfo supplierinfo) {
		this.supplierinfo = supplierinfo;
	}

	@ExcelField(title="采购数量", align=2, sort=200)
	public Integer getPurchNum() {
		return purchNum;
	}

	public void setPurchNum(Integer purchNum) {
		this.purchNum = purchNum;
	}

	@ExcelField(title="采购总价", align=2, sort=210)
	public String getPurchTotalprice() {
		return purchTotalprice;
	}

	public void setPurchTotalprice(String purchTotalprice) {
		this.purchTotalprice = purchTotalprice;
	}
	
	@Length(min=0, max=1, message="货物状态长度必须介于 0 和 1 之间")
	@ExcelField(title="货物状态", align=2, sort=220, dictType="material_unit")
	public String getPurchStatus() {
		return purchStatus;
	}

	public void setPurchStatus(String purchStatus) {
		this.purchStatus = purchStatus;
	}
	
	@Length(min=0, max=1, message="发票状态长度必须介于 0 和 1 之间")
	@ExcelField(title="发票状态", align=2, sort=230, dictType="material_unit")
	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	
	@Length(min=0, max=1, message="付款状态长度必须介于 0 和 1 之间")
	@ExcelField(title="付款状态", align=2, sort=240, dictType="material_unit")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	@ExcelField(title="备注", align=2, sort=250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=1, message="入库状态长度必须介于 0 和 1 之间")
	@ExcelField(title="入库状态", align=2, sort=260, dictType="material_unit")
	public String getEntryStatus() {
		return entryStatus;
	}

	public BigDecimal getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(BigDecimal surplusNum) {
		this.surplusNum = surplusNum;
	}

	public void setEntryStatus(String entryStatus) {
		this.entryStatus = entryStatus;
	}

	@Override
	public String toString() {
		return "FsMaterialPurchasingorderlistParam{" +
				"orderId=" + orderId +
				", materialId=" + materialId +
				", materialCode='" + materialCode + '\'' +
				", materialName='" + materialName + '\'' +
				", brand='" + brand + '\'' +
				", category='" + category + '\'' +
				", catalogprice='" + catalogprice + '\'' +
				", actualprice='" + actualprice + '\'' +
				", unitnum=" + unitnum +
				", unit='" + unit + '\'' +
				", supplierinfo=" + supplierinfo +
				", purchNum=" + purchNum +
				", purchTotalprice='" + purchTotalprice + '\'' +
				", purchStatus='" + purchStatus + '\'' +
				", invoiceStatus='" + invoiceStatus + '\'' +
				", payStatus='" + payStatus + '\'' +
				", description='" + description + '\'' +
				", entryStatus='" + entryStatus + '\'' +
				", objectIn=" + objectIn.doubleValue() +
				", surplusNum=" + surplusNum.doubleValue() +
				", warehouseinfo.id=" + warehouseinfo.getId() +
				", warehouseinfo.name=" + warehouseinfo.getName() +
				'}';
	}
}