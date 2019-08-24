/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.project.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.project.entity.FsSubproject;

/**
 * 子项目管理DAO接口
 * @author chenzhe
 * @version 2019-08-15
 */
@MyBatisDao
public interface FsSubprojectDao extends CrudDao<FsSubproject> {
	
}