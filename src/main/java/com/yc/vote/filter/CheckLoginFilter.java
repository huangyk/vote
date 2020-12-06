package com.yc.vote.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(value="/back/*", filterName = "CheckLoginFilter")
public class CheckLoginFilter implements Filter {
	
	public void init(FilterConfig config) throws ServletException {
		
	}

	/**
	 * 实现过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if (req.getSession().getAttribute("currentLoginUser") == null) { // 说明没有登录
			resp.setContentType("text/html;charset=utf-8");
			String path = request.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/login.html";
			PrintWriter out = resp.getWriter();
			out.print("<script>alert('请先登录...');location.href='" + path + "';</script>");
			out.flush();
			return;
		}
		
		String path = req.getRequestURI().replaceFirst(req.getContextPath(), ""); 
		req.getRequestDispatcher("../WEB-INF" + path).forward(req, resp);
	}
}
