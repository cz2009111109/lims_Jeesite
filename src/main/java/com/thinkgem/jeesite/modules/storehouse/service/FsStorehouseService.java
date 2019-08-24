/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.storehouse.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.storehouse.entity.FsStorehouse;
import com.thinkgem.jeesite.modules.storehouse.dao.FsStorehouseDao;

/**
 * 仓库位置Service
 * @author chenzhe
 * @version 2019-08-12
 */
@Service
@Transactional(readOnly = true)
public class FsStorehouseService extends TreeService<FsStorehouseDao, FsStorehouse> {

	@Override
	public FsStorehouse get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsStorehouse> findList(FsStorehouse fsStorehouse) {
		if (StringUtils.isNotBlank(fsStorehouse.getParentIds())){
			fsStorehouse.setParentIds(","+fsStorehouse.getParentIds()+",");
		}
		return super.findList(fsStorehouse);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsStorehouse fsStorehouse) {
		super.save(fsStorehouse);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsStorehouse fsStorehouse) {
		super.delete(fsStorehouse);
	}
	
}