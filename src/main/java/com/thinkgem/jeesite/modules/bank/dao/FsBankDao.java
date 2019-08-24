/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bank.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bank.entity.FsBank;

/**
 * 开票银行账号DAO接口
 * @author chenzhe
 * @version 2019-08-07
 */
@MyBatisDao
public interface FsBankDao extends CrudDao<FsBank> {
	
}