/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.task.entity.FsExtractionDetectionTask;
import com.thinkgem.jeesite.modules.task.dao.FsExtractionDetectionTaskDao;

/**
 * 提取检测任务Service
 * @author chenzhe
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class FsExtractionDetectionTaskService extends CrudService<FsExtractionDetectionTaskDao, FsExtractionDetectionTask> {

	@Override
	public FsExtractionDetectionTask get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsExtractionDetectionTask> findList(FsExtractionDetectionTask fsExtractionDetectionTask) {
		return super.findList(fsExtractionDetectionTask);
	}

	@Override
	public Page<FsExtractionDetectionTask> findPage(Page<FsExtractionDetectionTask> page, FsExtractionDetectionTask fsExtractionDetectionTask) {
		return super.findPage(page, fsExtractionDetectionTask);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsExtractionDetectionTask fsExtractionDetectionTask) {
		super.saveMaxId(fsExtractionDetectionTask);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsExtractionDetectionTask fsExtractionDetectionTask) {
		super.delete(fsExtractionDetectionTask);
	}

	@Transactional(readOnly = false)
	@Override
	public int insert(FsExtractionDetectionTask fsExtractionDetectionTask) {
		return	super.insert(fsExtractionDetectionTask);
	}

	@Transactional(readOnly = false)
	@Override
	public int update(FsExtractionDetectionTask fsExtractionDetectionTask) {
		return	super.update(fsExtractionDetectionTask);
	}

}