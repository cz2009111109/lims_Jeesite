/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.good.entity.FsGoodCategory;
import com.thinkgem.jeesite.modules.good.dao.FsGoodCategoryDao;

/**
 * 商品类别Service
 * @author chenzhe
 * @version 2019-08-09
 */
@Service
@Transactional(readOnly = true)
public class FsGoodCategoryService extends CrudService<FsGoodCategoryDao, FsGoodCategory> {

	@Override
	public FsGoodCategory get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsGoodCategory> findList(FsGoodCategory fsGoodCategory) {
		return super.findList(fsGoodCategory);
	}

	@Override
	public Page<FsGoodCategory> findPage(Page<FsGoodCategory> page, FsGoodCategory fsGoodCategory) {
		return super.findPage(page, fsGoodCategory);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsGoodCategory fsGoodCategory) {
		super.saveMaxId(fsGoodCategory);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsGoodCategory fsGoodCategory) {
		super.delete(fsGoodCategory);
	}
	
}