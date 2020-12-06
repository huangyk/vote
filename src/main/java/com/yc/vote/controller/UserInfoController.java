package com.yc.vote.controller;


import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.vote.bean.UserInfo;
import com.yc.vote.biz.IUserInfoBiz;
import com.yc.vote.biz.impl.UserInfoBizImpl;
import com.yc.vote.util.RequestParamUtil;

@WebServlet("/user/*")
public class UserInfoController extends BasicController{
	private static final long serialVersionUID = 1L;

	// 这个方法通过  /user/reg 访问
	public void reg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserInfo uf = RequestParamUtil.getParams(UserInfo.class, request);

		// 调用业务模型层
		IUserInfoBiz userInfoBiz = new UserInfoBizImpl();
		int result = userInfoBiz.add(uf);
		if (result > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	// /user/login  
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserInfo uf = RequestParamUtil.getParams(UserInfo.class, request);

		// 调用业务模型层
		IUserInfoBiz userInfoBiz = new UserInfoBizImpl();
		UserInfo userInfo = userInfoBiz.login(uf);
		if (userInfo != null) {
			request.getSession().setAttribute("currentLoginUser", userInfo);
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}
	
	/**
	 * 检查登录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute("currentLoginUser");
		if (obj == null) {
			this.send(response, 500, "失败");
			return;
		}
		this.send(response, 200, obj);
	}
}
