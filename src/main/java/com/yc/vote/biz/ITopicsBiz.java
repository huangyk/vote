package com.yc.vote.biz;

import java.util.List;

import com.yc.vote.bean.Topics;

public interface ITopicsBiz {
	public int add(Topics topic);
	
	public List<Topics> findAll();

	public Topics findByTid(String tid);
	
	public int update(String tid, String inos, Integer usid);
}
