/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reprot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.reprot.entity.FsExtractionDetectionReport;
import com.thinkgem.jeesite.modules.reprot.dao.FsExtractionDetectionReportDao;

/**
 * 提取检测报告Service
 * @author chenzhe
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class FsExtractionDetectionReportService extends CrudService<FsExtractionDetectionReportDao, FsExtractionDetectionReport> {

	@Override
	public FsExtractionDetectionReport get(String id) {
		return super.get(id);
	}

	@Override
	public List<FsExtractionDetectionReport> findList(FsExtractionDetectionReport fsExtractionDetectionReport) {
		return super.findList(fsExtractionDetectionReport);
	}

	@Override
	public Page<FsExtractionDetectionReport> findPage(Page<FsExtractionDetectionReport> page, FsExtractionDetectionReport fsExtractionDetectionReport) {
		return super.findPage(page, fsExtractionDetectionReport);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(FsExtractionDetectionReport fsExtractionDetectionReport) {
		super.saveMaxId(fsExtractionDetectionReport);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(FsExtractionDetectionReport fsExtractionDetectionReport) {
		super.delete(fsExtractionDetectionReport);
	}

	@Transactional(readOnly = false)
	@Override
	public int insert(FsExtractionDetectionReport fsExtractionDetectionReport) {
		return	super.insert(fsExtractionDetectionReport);
	}

	@Transactional(readOnly = false)
	@Override
	public int update(FsExtractionDetectionReport fsExtractionDetectionReport) {
		return	super.update(fsExtractionDetectionReport);
	}

}