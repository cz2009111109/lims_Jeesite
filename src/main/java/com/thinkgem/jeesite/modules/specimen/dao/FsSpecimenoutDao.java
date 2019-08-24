/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.specimen.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimenout;

/**
 * 样本出库DAO接口
 * @author chenzhe
 * @version 2019-08-13
 */
@MyBatisDao
public interface FsSpecimenoutDao extends CrudDao<FsSpecimenout> {
	
}