package com.thinkgem.jeesite.common.dataSource;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @ProjectName: permission
 * @Package: com.frasergen.context
 * @ClassName: DataSource
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2019/5/17 9:21
 * @Version: 1.0
 */
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface DataSource {
	public static String DATA1 = "dataSource";
	public static String DATA2 = "dataSource2";
	
    String value() default  DataSource.DATA1;
}
