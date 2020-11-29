package com.yc.vote.biz;

import com.yc.vote.bean.UserInfo;

public interface IUserInfoBiz {
	public int add(UserInfo uf);
	
	public UserInfo login(UserInfo uf);
}
