/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.good.entity.FsGood;

/**
 * 商品列表DAO接口
 * @author chenzhe
 * @version 2019-08-11
 */
@MyBatisDao
public interface FsGoodDao extends CrudDao<FsGood> {
	
}