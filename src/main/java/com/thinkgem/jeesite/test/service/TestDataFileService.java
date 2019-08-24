/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.test.entity.TestDataFile;
import com.thinkgem.jeesite.test.dao.TestDataFileDao;

/**
 * 测试附件Service
 * @author chenzhe
 * @version 2019-08-07
 */
@Service
@Transactional(readOnly = true)
public class TestDataFileService extends CrudService<TestDataFileDao, TestDataFile> {

	@Override
	public TestDataFile get(String id) {
		return super.get(id);
	}

	@Override
	public List<TestDataFile> findList(TestDataFile testDataFile) {
		return super.findList(testDataFile);
	}

	@Override
	public Page<TestDataFile> findPage(Page<TestDataFile> page, TestDataFile testDataFile) {
		return super.findPage(page, testDataFile);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(TestDataFile testDataFile) {
		super.saveMaxId(testDataFile);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(TestDataFile testDataFile) {
		super.delete(testDataFile);
	}
	
}