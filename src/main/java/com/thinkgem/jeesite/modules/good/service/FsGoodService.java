/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.good.entity.FsGood;
import com.thinkgem.jeesite.modules.good.dao.FsGoodDao;

/**
 * 商品列表Service
 * @author chenzhe
 * @version 2019-08-11
 */
@Service
@Transactional(readOnly = true)
public class FsGoodService extends CrudService<FsGoodDao, FsGood> {

	@Override
	public FsGood get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsGood> findList(FsGood fsGood) {
		return super.findList(fsGood);
	}

	@Override
	public Page<FsGood> findPage(Page<FsGood> page, FsGood fsGood) {
		return super.findPage(page, fsGood);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsGood fsGood) {
		super.saveMaxId(fsGood);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsGood fsGood) {
		super.delete(fsGood);
	}

	public List<FsGood> findByIds(String[] ids){
		return  dao.findByIds(ids);
	}

	public List<FsGood> findByIds(Set<String> ids){
		return  dao.findByIdsSet(ids);
	}

	public List<FsGood> findByIds(List<String> ids){
		return  dao.findByIdsList(ids);
	}
}