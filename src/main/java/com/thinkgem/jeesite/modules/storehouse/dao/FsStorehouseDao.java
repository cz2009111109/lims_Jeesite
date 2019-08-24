/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.storehouse.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.storehouse.entity.FsStorehouse;

/**
 * 仓库位置DAO接口
 * @author chenzhe
 * @version 2019-08-12
 */
@MyBatisDao
public interface FsStorehouseDao extends TreeDao<FsStorehouse> {
	
}