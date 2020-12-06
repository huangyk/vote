package com.yc.vote.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.vote.bean.Topics;
import com.yc.vote.bean.UserInfo;
import com.yc.vote.biz.ITopicsBiz;
import com.yc.vote.biz.impl.TopicsBizImpl;
import com.yc.vote.util.RequestParamUtil;

@WebServlet("/topic/*")
public class TopicsController extends BasicController{
	private static final long serialVersionUID = 1L;

	public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute("currentLoginUser");
		if (obj == null) {
			this.send(response, 500, "失败");
			return;
		}
		
		UserInfo uf = (UserInfo) obj;
		Topics topic = RequestParamUtil.getParams(Topics.class, request);
		topic.setUsid(uf.getUsid());
		
		ITopicsBiz topicsBiz = new TopicsBizImpl();
		int result = topicsBiz.add(topic);
		if (result > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}
	
	// 查询所有
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITopicsBiz topicsBiz = new TopicsBizImpl();
		List<Topics> list = topicsBiz.findAll();
		if (list == null || list.isEmpty()) {
			this.send(response, 500, "空");
			return;
		}
		this.send(response, 200, list);
	}
	
	public void findByTid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		ITopicsBiz topicsBiz = new TopicsBizImpl();
		Topics topic = topicsBiz.findByTid(tid);
		if (topic == null) {
			this.send(response, 500, "空");
			return;
		}
		this.send(response, 200, topic);
	}
	
	public void vote(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute("currentLoginUser");
		if (obj == null) {
			this.send(response, 500, "失败");
			return;
		}
		
		UserInfo uf = (UserInfo) obj;
		String tid = request.getParameter("tid");
		String inos = request.getParameter("inos");
		
		ITopicsBiz topicsBiz = new TopicsBizImpl();
		int result = topicsBiz.update(tid, inos, uf.getUsid());
		if (result <= 0) {
			this.send(response, 500, "失败");
			return;
		}
		this.send(response, 200, "成功");
	}
 }
