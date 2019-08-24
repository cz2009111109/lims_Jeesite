package com.thinkgem.jeesite.common.dataSource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

public class MultipleDataSourceAspect {
	
	/**  
     * 必须为final String类型的,注解里要使用的变量只能是静态常量类型的  
     */  
    public static final String EDP="execution(* com.spring.service.impl.UserManagerServiceImpl..*(..))";
	
	@Before("EDP")  
    public void doBefore(JoinPoint point) {  
		 Class<?> target = point.getTarget().getClass();
	        MethodSignature signature = (MethodSignature) point.getSignature();
	        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
	        for (Class<?> clazz : target.getInterfaces()) {
	            resolveDataSource(clazz, signature.getMethod());
	        }
	        resolveDataSource(target, signature.getMethod());
	        System.out.println("切面拦截成功");
    }  
	
    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     *
     * @param clazz
     * @param method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource source = clazz.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(source.value());
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource source = m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(source.value());
            }
        } catch (Exception e) {
            System.out.println(clazz + ":" + e.getMessage());
        }
    }
}
