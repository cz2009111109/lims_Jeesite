/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.specimen.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.specimen.entity.FsSpecimen;

/**
 * 样本库存DAO接口
 * @author chenzhe
 * @version 2019-08-13
 */
@MyBatisDao
public interface FsSpecimenDao extends CrudDao<FsSpecimen> {
	
}