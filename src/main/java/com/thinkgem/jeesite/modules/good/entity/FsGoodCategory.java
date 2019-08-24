/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商品类别Entity
 * @author chenzhe
 * @version 2019-08-09
 */
public class FsGoodCategory extends DataEntity<FsGoodCategory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 类别名称
	private String description;		// 说明
	
	public FsGoodCategory() {
		super();
	}

	public FsGoodCategory(String id){
		super(id);
	}

	@Length(min=1, max=200, message="类别名称长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="说明长度必须介于 0 和 200 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}