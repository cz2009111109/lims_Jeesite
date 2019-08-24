/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cooperation.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cooperation.entity.FsGoodCooperation;

/**
 * 试剂供应商DAO接口
 * @author chenzhe
 * @version 2019-08-08
 */
@MyBatisDao
public interface FsGoodCooperationDao extends CrudDao<FsGoodCooperation> {
	
}