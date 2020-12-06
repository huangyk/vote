package com.yc.vote.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(value="/*", filterName = "CharacterEncodingFilter", initParams = {@WebInitParam(name="encoding", value="utf-8")} )
public class CharacterEncodingFilter implements Filter {
	private String encoding = "utf-8";
	
	public void init(FilterConfig config) throws ServletException {
		String temp = config.getInitParameter("encoding");
		if (temp == null || "".equals(temp)) {
			return;
		}
		
		encoding = temp;
	}

	/**
	 * 实现过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		
		// 一定要记得将这个请求和响应往下传递
		chain.doFilter(req, resp); // 调用下一个过滤器的过来方法
	}
}
