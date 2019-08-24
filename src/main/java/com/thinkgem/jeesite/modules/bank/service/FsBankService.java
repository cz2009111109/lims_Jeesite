/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bank.entity.FsBank;
import com.thinkgem.jeesite.modules.bank.dao.FsBankDao;

/**
 * 开票银行账号Service
 * @author chenzhe
 * @version 2019-08-07
 */
@Service
@Transactional(readOnly = true)
public class FsBankService extends CrudService<FsBankDao, FsBank> {

	@Override
	public FsBank get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsBank> findList(FsBank fsBank) {
		return super.findList(fsBank);
	}

	@Override
	public Page<FsBank> findPage(Page<FsBank> page, FsBank fsBank) {
		return super.findPage(page, fsBank);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsBank fsBank) {
		super.saveMaxId(fsBank);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsBank fsBank) {
		super.delete(fsBank);
	}
	
}