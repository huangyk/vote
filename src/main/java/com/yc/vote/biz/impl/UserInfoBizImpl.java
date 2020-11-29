package com.yc.vote.biz.impl;

import com.yc.vote.bean.UserInfo;
import com.yc.vote.biz.IUserInfoBiz;
import com.yc.vote.dao.IBaseDao;
import com.yc.vote.dao.impl.BaseDaoImpl;
import com.yc.vote.util.StringUtil;

public class UserInfoBizImpl implements IUserInfoBiz{
	@Override
	public int add(UserInfo uf) {
		if (StringUtil.checkNull(uf.getUname(), uf.getPwd())) {
			return -1;
		}
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.insert("UserInfo.add", uf);
	}

	@Override
	public UserInfo login(UserInfo uf) {
		if (StringUtil.checkNull(uf.getUname(), uf.getPwd())) {
			return null;
		}
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.find("UserInfo.login", uf);
	}

}
