/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 仓库库位信息Entity
 * @author chenzhe
 * @version 2019-08-26
 */
public class FsMaterialWarehouseinfo extends TreeEntity<FsMaterialWarehouseinfo> {
	
	private static final long serialVersionUID = 1L;
	private FsMaterialWarehouseinfo parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String warehouseCode;		// 仓库编号
	private String name;		// 仓库名称
	private String warehousePos;		// 仓库位置
	private Integer type;		// 仓库类型
	private Integer status;		// 仓库状态
	private Integer sort;		// 排序
	
	public FsMaterialWarehouseinfo() {
		super();
	}

	public FsMaterialWarehouseinfo(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public FsMaterialWarehouseinfo getParent() {
		return parent;
	}

	public void setParent(FsMaterialWarehouseinfo parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=50, message="仓库编号长度必须介于 0 和 50 之间")
	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	
	@Length(min=1, max=100, message="仓库名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="仓库位置长度必须介于 0 和 50 之间")
	public String getWarehousePos() {
		return warehousePos;
	}

	public void setWarehousePos(String warehousePos) {
		this.warehousePos = warehousePos;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}