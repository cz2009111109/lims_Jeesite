/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.entity;

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
	private FsGoodCooperation fsGoodCooperation;		// 供应商
	
	public FsGood() {
		super();
	}

	public FsGood(String id){
		super(id);
	}

	@Length(min=0, max=300, message="商品名称长度必须介于 0 和 300 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=300, message="品牌长度必须介于 0 和 300 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=300, message="货号长度必须介于 0 和 300 之间")
	public String getItemnumber() {
		return itemnumber;
	}

	public void setItemnumber(String itemnumber) {
		this.itemnumber = itemnumber;
	}
	
	public String getCatalogprice() {
		return catalogprice;
	}

	public void setCatalogprice(String catalogprice) {
		this.catalogprice = catalogprice;
	}
	
	public String getActualprice() {
		return actualprice;
	}

	public void setActualprice(String actualprice) {
		this.actualprice = actualprice;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Long getUnitnum() {
		return unitnum;
	}

	public void setUnitnum(Long unitnum) {
		this.unitnum = unitnum;
	}
	
	@Length(min=0, max=10, message="单位长度必须介于 0 和 10 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=0, max=200, message="出厂厂家长度必须介于 0 和 200 之间")
	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}
	
	@Length(min=0, max=300, message="功能用途长度必须介于 0 和 300 之间")
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
	
	@Length(min=0, max=300, message="说明长度必须介于 0 和 300 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=64, message="类别长度必须介于 0 和 64 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public FsGoodCooperation getFsGoodCooperation() {
		return fsGoodCooperation;
	}

	public void setFsGoodCooperation(FsGoodCooperation fsGoodCooperation) {
		this.fsGoodCooperation = fsGoodCooperation;
	}
	
}