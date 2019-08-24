/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.project.entity.FsProject;
import com.thinkgem.jeesite.modules.project.dao.FsProjectDao;

/**
 * 项目管理Service
 * @author chenzhe
 * @version 2019-08-15
 */
@Service
@Transactional(readOnly = true)
public class FsProjectService extends CrudService<FsProjectDao, FsProject> {

	@Override
	public FsProject get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsProject> findList(FsProject fsProject) {
		return super.findList(fsProject);
	}

	@Override
	public Page<FsProject> findPage(Page<FsProject> page, FsProject fsProject) {
		return super.findPage(page, fsProject);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsProject fsProject) {
		super.saveMaxId(fsProject);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsProject fsProject) {
		super.delete(fsProject);
	}

	@Transactional(readOnly = false)
	@Override
	public int insert(FsProject fsProject) {
		return	super.insert(fsProject);
	}

	@Transactional(readOnly = false)
	@Override
	public int update(FsProject fsProject) {
		return	super.update(fsProject);
	}

}