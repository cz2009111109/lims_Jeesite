/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.good.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.good.entity.FsGoodCategory;

/**
 * 商品类别DAO接口
 * @author chenzhe
 * @version 2019-08-09
 */
@MyBatisDao
public interface FsGoodCategoryDao extends CrudDao<FsGoodCategory> {
	
}