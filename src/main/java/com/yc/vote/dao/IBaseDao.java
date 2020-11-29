package com.yc.vote.dao;

import java.util.List;

public interface IBaseDao {
	public int insert(String sqlId, Object obj);
	
	public int update(String sqlId, Object obj);
	
	public int update(List<String> sqlIds, List<Object> objs);
	
	public int delete(String sqlId, Object obj);
	
	public <T> T find(String sqlId, Object obj);

	public <T> List<T> finds(String sqlId);
	
	public <T> List<T> finds(String sqlId, Object obj);
	
	public int total(String sqlId);
	
	public int total(String sqlId, Object obj);
}
