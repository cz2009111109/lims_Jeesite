/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.material.entity.FsMaterialSupplierinfo;
import com.thinkgem.jeesite.modules.material.dao.FsMaterialSupplierinfoDao;

/**
 * 供应商信息Service
 * @author chenzhe
 * @version 2019-08-26
 */
@Service
@Transactional(readOnly = true)
public class FsMaterialSupplierinfoService extends CrudService<FsMaterialSupplierinfoDao, FsMaterialSupplierinfo> {

	@Override
	public FsMaterialSupplierinfo get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsMaterialSupplierinfo> findList(FsMaterialSupplierinfo fsMaterialSupplierinfo) {
		return super.findList(fsMaterialSupplierinfo);
	}

	@Override
	public Page<FsMaterialSupplierinfo> findPage(Page<FsMaterialSupplierinfo> page, FsMaterialSupplierinfo fsMaterialSupplierinfo) {
		return super.findPage(page, fsMaterialSupplierinfo);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsMaterialSupplierinfo fsMaterialSupplierinfo) {
		super.saveMaxId(fsMaterialSupplierinfo);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsMaterialSupplierinfo fsMaterialSupplierinfo) {
		super.delete(fsMaterialSupplierinfo);
	}

	@Transactional(readOnly = false)
	@Override
	public int insert(FsMaterialSupplierinfo fsMaterialSupplierinfo) {
		return	super.insert(fsMaterialSupplierinfo);
	}

	@Transactional(readOnly = false)
	@Override
	public int update(FsMaterialSupplierinfo fsMaterialSupplierinfo) {
		return	super.update(fsMaterialSupplierinfo);
	}

}