/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.project.entity.FsSubproject;
import com.thinkgem.jeesite.modules.project.dao.FsSubprojectDao;

/**
 * 子项目管理Service
 * @author chenzhe
 * @version 2019-08-15
 */
@Service
@Transactional(readOnly = true)
public class FsSubprojectService extends CrudService<FsSubprojectDao, FsSubproject> {

	@Override
	public FsSubproject get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsSubproject> findList(FsSubproject fsSubproject) {
		return super.findList(fsSubproject);
	}

	@Override
	public Page<FsSubproject> findPage(Page<FsSubproject> page, FsSubproject fsSubproject) {
		return super.findPage(page, fsSubproject);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsSubproject fsSubproject) {
		super.saveMaxId(fsSubproject);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsSubproject fsSubproject) {
		super.delete(fsSubproject);
	}

	@Transactional(readOnly = false)
	@Override
	public int insert(FsSubproject fsSubproject) {
		return	super.insert(fsSubproject);
	}

	@Transactional(readOnly = false)
	@Override
	public int update(FsSubproject fsSubproject) {
		return	super.update(fsSubproject);
	}

}