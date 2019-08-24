/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.test.entity.TestDataChild;
import com.thinkgem.jeesite.test.dao.TestDataChildDao;

/**
 * 主子表子表Service
 * @author chenzhe
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TestDataChildService extends CrudService<TestDataChildDao, TestDataChild> {

	@Override
	public TestDataChild get(String id) {
		return super.get(id);
	}

	@Override
	public List<TestDataChild> findList(TestDataChild testDataChild) {
		return super.findList(testDataChild);
	}

	@Override
	public Page<TestDataChild> findPage(Page<TestDataChild> page, TestDataChild testDataChild) {
		return super.findPage(page, testDataChild);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(TestDataChild testDataChild) {
		super.saveMaxId(testDataChild);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(TestDataChild testDataChild) {
		super.delete(testDataChild);
	}

	@Transactional(readOnly = false)
	@Override
	public int insert(TestDataChild testDataChild) {
		return	super.insert(testDataChild);
	}

	@Transactional(readOnly = false)
	@Override
	public int update(TestDataChild testDataChild) {
		return	super.update(testDataChild);
	}

}