/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.material.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.material.entity.FsMaterialWarehouseinfo;

/**
 * 仓库库位信息DAO接口
 * @author chenzhe
 * @version 2019-08-26
 */
@MyBatisDao
public interface FsMaterialWarehouseinfoDao extends TreeDao<FsMaterialWarehouseinfo> {
	
}