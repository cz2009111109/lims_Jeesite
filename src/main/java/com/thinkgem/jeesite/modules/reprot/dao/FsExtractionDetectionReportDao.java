/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.reprot.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.reprot.entity.FsExtractionDetectionReport;

/**
 * 提取检测报告DAO接口
 * @author chenzhe
 * @version 2019-08-16
 */
@MyBatisDao
public interface FsExtractionDetectionReportDao extends CrudDao<FsExtractionDetectionReport> {
	
}