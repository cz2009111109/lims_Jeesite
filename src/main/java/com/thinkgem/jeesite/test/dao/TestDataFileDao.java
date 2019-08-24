/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.test.entity.TestDataFile;

/**
 * 测试附件DAO接口
 * @author chenzhe
 * @version 2019-08-07
 */
@MyBatisDao
public interface TestDataFileDao extends CrudDao<TestDataFile> {
	
}