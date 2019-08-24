/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.task.entity.FsExtractionDetectionTask;

/**
 * 提取检测任务DAO接口
 * @author chenzhe
 * @version 2019-08-16
 */
@MyBatisDao
public interface FsExtractionDetectionTaskDao extends CrudDao<FsExtractionDetectionTask> {
	
}