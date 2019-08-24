package com.thinkgem.jeesite.common.persistence.dialect.db;


import com.thinkgem.jeesite.common.persistence.dialect.Dialect;
import com.thinkgem.jeesite.common.persistence.dialect.db.MySQLDialect;
import com.thinkgem.jeesite.common.persistence.dialect.db.SQLServer2005Dialect;


public class DbDialectFoactory {
	
	 public static Dialect createDbDialect(String type) {
	        if ("sqlserver".equals(type)) {
	            return new SQLServer2005Dialect();
	        }
	        else{
	            return new MySQLDialect();
	        }
	    }
	
}
