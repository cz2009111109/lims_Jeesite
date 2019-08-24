/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.specimen.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimen;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimenin;
import com.thinkgem.jeesite.modules.specimen.dao.FsSpecimeninDao;

/**
 * 样本入库Service
 * @author chenzhe
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class FsSpecimeninService extends CrudService<FsSpecimeninDao, FsSpecimenin> {

	@Autowired
	private FsSpecimenService fsSpecimenService;


	@Override
	public FsSpecimenin get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsSpecimenin> findList(FsSpecimenin fsSpecimenin) {
		return super.findList(fsSpecimenin);
	}

	@Override
	public Page<FsSpecimenin> findPage(Page<FsSpecimenin> page, FsSpecimenin fsSpecimenin) {
		return super.findPage(page, fsSpecimenin);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsSpecimenin fsSpecimenin)  {
		super.save(fsSpecimenin);
		FsSpecimen fsSpecimen=new FsSpecimen();
		BeanUtils.copyProperties(fsSpecimenin,fsSpecimen);
		fsSpecimen.setId(fsSpecimenService.getMaxId());
		fsSpecimen.setFsSpecimenin(fsSpecimenin);
		fsSpecimenService.insert(fsSpecimen);

	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsSpecimenin fsSpecimenin) {
		super.delete(fsSpecimenin);
	}



}