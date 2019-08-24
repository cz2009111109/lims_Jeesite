package com.thinkgem.jeesite.common.dataSource;

import java.lang.reflect.Method;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class DataSourceAdvisor implements MethodBeforeAdvice, AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		if(method.isAnnotationPresent(DataSource.class)) {
            
            System.out.println("afterReturning方法上有注解");
        }else {
        	System.out.println("afterReturning方法上没有注解");
        }
		DynamicDataSourceHolder.clearDataSource();
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		if(method.isAnnotationPresent(DataSource.class)){
			System.out.println("before方法上有注解");
		}else {
			System.out.println("before方法上没有注解");
		}
		System.out.println("method----"+method.getName());
		System.out.println("args----"+args.toString());
		System.out.println("args name----"+args.getClass().getName());
		System.out.println("target----"+target.getClass().getName());
		Class<?> classtarget =target.getClass();
		 for (Class<?> clazz : classtarget.getInterfaces()) {
			 System.out.println("classtarget---"+clazz.getName());
			 if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource source = clazz.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(source.value());
                System.out.println("DataSourceAdvisor切面拦截成功 默认使用类型注解"+clazz.getName()+"--"+source.value()+"--"+method.getName()+"---"+DynamicDataSourceHolder.getDataSource());
            }
			/* Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
	            	
            }*/
		 }
		 
	}
	
	

}
