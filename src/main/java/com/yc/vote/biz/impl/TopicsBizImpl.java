package com.yc.vote.biz.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.yc.vote.bean.Topics;
import com.yc.vote.biz.ITopicsBiz;
import com.yc.vote.dao.IBaseDao;
import com.yc.vote.dao.impl.BaseDaoImpl;
import com.yc.vote.util.StringUtil;

public class TopicsBizImpl implements ITopicsBiz {

	@Override
	public int add(Topics topic) {
		if (StringUtil.checkNull(topic.getEdate(), topic.getSdate(), topic.getTname(), topic.getUname())) {
			return -1;
		}
		
		String tid = UUID.randomUUID().toString().replace("-", "");
		topic.setTid(tid);
		
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		String[] inames = topic.getUname().split(";");
		for (String iname : inames) {
			map = new HashMap<String, Object>();
			map.put("tid", tid);
			map.put("iname", iname);
			params.add(map);
		}
		
		List<String> sqlIds = new ArrayList<String>();
		Collections.addAll(sqlIds, "Topics.add", "TopicItem.add");
		
		List<Object> parameters = new ArrayList<Object>();
		Collections.addAll(parameters, topic, params);
		
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.update(sqlIds, parameters);
	}

	@Override
	public List<Topics> findAll() {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.finds("Topics.findAll");
	}

	@Override
	public Topics findByTid(String tid) {
		if (StringUtil.checkNull(tid)) {
			return null;
		}
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.find("Topics.findByTid", tid);
	}

	@Override
	public int update(String tid, String inos, Integer usid) {
		if (StringUtil.checkNull(tid, inos)) {
			return -1;
		}
		
		List<String> sqlIds = new ArrayList<String>();
		Collections.addAll(sqlIds, "Topics.update", "TopicItem.update");
		
		Map<String, Object> topic = new HashMap<String, Object>();
		topic.put("usid", usid);
		topic.put("tid", tid);
		
		List<Object> parameters = new ArrayList<Object>();
		Collections.addAll(parameters, topic, inos.split(","));
		
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.update(sqlIds, parameters);
	}
}
