/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.material.entity.FsMaterialWarehouseinfo;
import com.thinkgem.jeesite.modules.material.dao.FsMaterialWarehouseinfoDao;

/**
 * 仓库库位信息Service
 * @author chenzhe
 * @version 2019-08-26
 */
@Service
@Transactional(readOnly = true)
public class FsMaterialWarehouseinfoService extends TreeService<FsMaterialWarehouseinfoDao, FsMaterialWarehouseinfo> {

	@Override
	public FsMaterialWarehouseinfo get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsMaterialWarehouseinfo> findList(FsMaterialWarehouseinfo fsMaterialWarehouseinfo) {
		if (StringUtils.isNotBlank(fsMaterialWarehouseinfo.getParentIds())){
			fsMaterialWarehouseinfo.setParentIds(","+fsMaterialWarehouseinfo.getParentIds()+",");
		}
		return super.findList(fsMaterialWarehouseinfo);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsMaterialWarehouseinfo fsMaterialWarehouseinfo) {
		super.save(fsMaterialWarehouseinfo);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsMaterialWarehouseinfo fsMaterialWarehouseinfo) {
		super.delete(fsMaterialWarehouseinfo);
	}
	
}