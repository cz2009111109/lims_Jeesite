/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.entity;

import com.thinkgem.jeesite.common.supcan.annotation.treelist.cols.SupCol;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.material.entity.FsMaterialSupplierinfo;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.cooperation.entity.FsGoodCooperation;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品列表Entity
 * @author chenzhe
 * @version 2019-08-11
 */
public class FsGood extends DataEntity<FsGood> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商品名称
	private String brand;		// 品牌
	private String itemnumber;		// 货号
	private String catalogprice;		// 目录价格
	private String actualprice;		// 实际价格
	private Integer state;		// 状态
	private Long unitnum;		// 商品规格
	private String unit;		// 单位
	private String factory;		// 出厂厂家
	private String function;		// 功能用途
	private String description;		// 说明
	private String category;		// 类别
	private FsMaterialSupplierinfo supplierinfo;		// 供应商
	
	public FsGood() {
		super();
	}


	public FsGood(String id){
		super(id);
	}

	@SupCol(isUnique="true", isHide="true")
	@ExcelField(title="ID", type=1, align=2, sort=1)
	@Override
	public String getId() {
		return super.getId();
	}

	@Length(min=0, max=300, message="商品名称长度必须介于 0 和 300 之间")
	@ExcelField(title="名称", align=2, sort=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=300, message="品牌长度必须介于 0 和 300 之间")
	@ExcelField(title="品牌", align=2, sort=20)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=300, message="货号长度必须介于 0 和 300 之间")
	@ExcelField(title="货号", align=2, sort=30)
	public String getItemnumber() {
		return itemnumber;
	}

	public void setItemnumber(String itemnumber) {
		this.itemnumber = itemnumber;
	}

	@ExcelField(title="目录价", align=2, sort=50)
	public String getCatalogprice() {
		return catalogprice;
	}

	public void setCatalogprice(String catalogprice) {
		this.catalogprice = catalogprice;
	}

	@ExcelField(title="实际价", align=2, sort=40)
	public String getActualprice() {
		return actualprice;
	}

	public void setActualprice(String actualprice) {
		this.actualprice = actualprice;
	}

	@ExcelField(title="状态", align=2, sort=60)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ExcelField(title="规格", align=2, sort=70)
	public Long getUnitnum() {
		return unitnum;
	}

	public void setUnitnum(Long unitnum) {
		this.unitnum = unitnum;
	}
	
	@Length(min=0, max=10, message="单位长度必须介于 0 和 10 之间")
	@ExcelField(title="单位", align=2, sort=80)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=0, max=200, message="出厂厂家长度必须介于 0 和 200 之间")
	@ExcelField(title="出厂厂家", align=2, sort=90)
	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}
	
	@Length(min=0, max=300, message="功能用途长度必须介于 0 和 300 之间")
	@ExcelField(title="功能", align=2, sort=100)
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
	
	@Length(min=0, max=300, message="说明长度必须介于 0 和 300 之间")
	@ExcelField(title="说明", align=2, sort=110)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=64, message="类别长度必须介于 0 和 64 之间")
	@ExcelField(title="类别", align=2, sort=50, dictType="good_category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@ExcelField(title="供应商", align=2, sort=90)
	public FsMaterialSupplierinfo getSupplierinfo() {
		return supplierinfo;
	}


	public void setSupplierinfo(FsMaterialSupplierinfo supplierinfo) {
		this.supplierinfo = supplierinfo;
	}
	
}