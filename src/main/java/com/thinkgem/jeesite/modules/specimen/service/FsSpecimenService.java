/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.specimen.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimen;
import com.thinkgem.jeesite.modules.specimen.dao.FsSpecimenDao;

/**
 * 样本库存Service
 * @author chenzhe
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class FsSpecimenService extends CrudService<FsSpecimenDao, FsSpecimen> {

	@Override
	public FsSpecimen get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsSpecimen> findList(FsSpecimen fsSpecimen) {
		return super.findList(fsSpecimen);
	}

	@Override
	public Page<FsSpecimen> findPage(Page<FsSpecimen> page, FsSpecimen fsSpecimen) {
		return super.findPage(page, fsSpecimen);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsSpecimen fsSpecimen) {
		super.save(fsSpecimen);
	}

	@Transactional(readOnly = false)
	@Override
	public int insert(FsSpecimen fsSpecimen) {
		return	super.insert(fsSpecimen);
	}

	@Transactional(readOnly = false)
	@Override
	public int update(FsSpecimen fsSpecimen) {
		return	super.update(fsSpecimen);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(FsSpecimen fsSpecimen) {
		super.delete(fsSpecimen);
	}

	
}