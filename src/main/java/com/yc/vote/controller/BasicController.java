package com.yc.vote.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BasicController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI(); // 取到请求的资源地址   /votesys/user/login
		String path = url.substring(url.lastIndexOf("/") + 1); // 截取最后的路径，即处理这个请求的方法名
		
		// 先要获取到这个类中的所有方法
		Method[] methods = this.getClass().getDeclaredMethods();
		
		// 根据path找到对应的方法
		for (Method method : methods) {
			if (path.equals(method.getName())) { // 说明找到了对应方法
				// 反向激活运行这个方法
				try {
					method.invoke(this, request, response);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return;
			}
		}
		this.error(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * 返回一个状态码的方法
	 * @param response
	 * @param status 要返回的状态码
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, int status) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域访问
		response.setContentType("text/plain;charset=utf-8"); // 告诉浏览器是以普通文本返回的
		PrintWriter out = response.getWriter();
		out.print(status);
		out.flush();
	}

	/**
	 * 以json格式回送一个对象
	 * @param response
	 * @param obj 要返回的对象
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, Object obj) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		out.print(gson.toJson(obj));
		out.flush();
	}
	
	/**
	 * 返回一个普通字符串
	 * @param response
	 * @param str
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, String str) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(str);
		out.flush();
	}

	/**
	 * 以json格式回送数据的方法，返回一个状态码code和一个提示信息msg
	 * @param response
	 * @param code 状态码
	 * @param msg 要返回值的字符串信息
	 * @throws IOException 
	 */
	protected void send(HttpServletResponse response, int code, String msg) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		out.print(gson.toJson(map));
		out.flush();
	}

	/**
	 * 以json格式回送数据的方法，返回一个状态码code和一些数据
	 * @param response
	 * @param code 状态码
	 * @param data 要返回的数据信息
	 * @throws IOException 
	 */
	protected void send(HttpServletResponse response, int code, Object data) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("data", data);
		out.print(gson.toJson(map));
		out.flush();
	}

	protected void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		out.print("<script>alert('该请求不支持...');location.href='" + basePath + "index.html';</script>");
		out.flush();
		out.close();
	}
}
