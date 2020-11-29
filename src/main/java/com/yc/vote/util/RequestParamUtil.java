package com.yc.vote.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestParamUtil {
	public static <T> T getParams(Class<T> cls, HttpServletRequest request) {
		T t = null;
		
		try {
			t = cls.newInstance(); // 实例化对象
			
			// 获取请求中的参数的参数名，即表单中文本框的name属性的属性值
			Enumeration<String> names = request.getParameterNames();
			
			// 获取指定类的setter方法
			Method[] methods = cls.getMethods();
			Map<String, Method> setters = new HashMap<String, Method>();
			
			String methodName = null;
			for (Method md : methods) {
				methodName = md.getName();
				if (methodName.startsWith("set")) {
					setters.put(methodName, md);
				}
			}
			
			String name = null;
			Class<?>[] types = null;
			Class<?> type = null;
			Method method = null;
			while (names.hasMoreElements()) {
				name = names.nextElement(); // 获取一个请求参数名
				methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				
				method = setters.getOrDefault(methodName, null); // 根据方法名查找对应的方法
				if (method == null) { // 如果没有对应的方法，说明这个属性不需要注值，那么就不管
					continue;
				}
				
				types = method.getParameterTypes(); // 如果有，则获取这个方法的形参类型列表
				if (types == null || types.length <= 0) { // 如果没有形参，则也不能注值
					continue;
				}
				
				type = types[0]; // 如果有则取第一个形参的类型
				
				if (Integer.TYPE == type) {
					method.invoke(t, Integer.parseInt(request.getParameter(name)));
				} else if(Integer.class == type) { // 说明要的是一个整形值或一个整形对象
					method.invoke(t, Integer.valueOf(request.getParameter(name)));
				} else if (Float.TYPE == type || Float.class == type) {
					method.invoke(t, Float.parseFloat(request.getParameter(name)));
				} else if (Double.TYPE == type || Double.class == type) {
					method.invoke(t,Double.parseDouble(request.getParameter(name)));
				} else {
					method.invoke(t, request.getParameter(name));
				}
			}
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		return t;
	}
}
