/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.specimen.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimenout;
import com.thinkgem.jeesite.modules.specimen.dao.FsSpecimenoutDao;

/**
 * 样本出库Service
 * @author chenzhe
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class FsSpecimenoutService extends CrudService<FsSpecimenoutDao, FsSpecimenout> {

	@Override
	public FsSpecimenout get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsSpecimenout> findList(FsSpecimenout fsSpecimenout) {
		return super.findList(fsSpecimenout);
	}

	@Override
	public Page<FsSpecimenout> findPage(Page<FsSpecimenout> page, FsSpecimenout fsSpecimenout) {
		return super.findPage(page, fsSpecimenout);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsSpecimenout fsSpecimenout) {
		super.saveMaxId(fsSpecimenout);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsSpecimenout fsSpecimenout) {
		super.delete(fsSpecimenout);
	}
	
}