/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cooperation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cooperation.entity.FsGoodCooperation;
import com.thinkgem.jeesite.modules.cooperation.dao.FsGoodCooperationDao;

/**
 * 试剂供应商Service
 * @author chenzhe
 * @version 2019-08-08
 */
@Service
@Transactional(readOnly = true)
public class FsGoodCooperationService extends CrudService<FsGoodCooperationDao, FsGoodCooperation> {

	@Override
	public FsGoodCooperation get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsGoodCooperation> findList(FsGoodCooperation fsGoodCooperation) {
		return super.findList(fsGoodCooperation);
	}

	@Override
	public Page<FsGoodCooperation> findPage(Page<FsGoodCooperation> page, FsGoodCooperation fsGoodCooperation) {
		return super.findPage(page, fsGoodCooperation);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsGoodCooperation fsGoodCooperation) {
		super.saveMaxId(fsGoodCooperation);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsGoodCooperation fsGoodCooperation) {
		super.delete(fsGoodCooperation);
	}
	
}