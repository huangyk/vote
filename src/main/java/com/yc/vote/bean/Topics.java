package com.yc.vote.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yc.vote.util.StringUtil;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年9月3日
 * Email haijunzhou@hnit.edu.cn
 */
public class Topics implements Serializable{
	private static final long serialVersionUID = -2810586064040806067L;
	private String tid;
	private String tname;
	private Integer types;
	private Integer usid;
	private String sdate;
	private String edate;
	private String usids;
	private Integer status = 0;  // 1、未开始   2、已结束    3、已投票
	private Integer count = 0;
	
	private String uname; // 用户名
	private List<TopicItem> topicItems;
	
	@Override
	public String toString() {
		return "Topics [tid=" + tid + ", tname=" + tname + ", types=" + types + ", usid=" + usid + ", sdate=" + sdate
				+ ", edate=" + edate + ", usids=" + usids + ", uname=" + uname + "]";
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Integer getTypes() {
		return types;
	}

	public void setTypes(Integer types) {
		this.types = types;
	}

	public Integer getUsid() {
		return usid;
	}

	public void setUsid(Integer usid) {
		this.usid = usid;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
		
		try {
			// 判断是否已经开始投票了
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = sdf.parse(sdate);
			Date date1 = new Date();
			if (date.after(date1)) { // 如果开始投票的日期在当前日期之后，说明还没开始
				this.status = 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
		
		try {
			// 判断是否已经开始投票了
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = sdf.parse(edate);
			Date date1 = new Date();
			if (date.before(date1)) { // 如果结束投票的日期在当前日期之后，说明还没开始
				this.status = 2;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getUsids() {
		return usids;
	}

	public void setUsids(String usids) {
		if (!StringUtil.checkNull(usids)) {
			usids = usids.substring(1);
		}
		this.usids = usids;
		
		if (!StringUtil.checkNull(usids)) {
			this.count = usids.split("&").length;
		}
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public List<TopicItem> getTopicItems() {
		return topicItems;
	}

	public void setTopicItems(List<TopicItem> topicItems) {
		this.topicItems = topicItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edate == null) ? 0 : edate.hashCode());
		result = prime * result + ((sdate == null) ? 0 : sdate.hashCode());
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
		result = prime * result + ((tname == null) ? 0 : tname.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
		result = prime * result + ((usid == null) ? 0 : usid.hashCode());
		result = prime * result + ((usids == null) ? 0 : usids.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topics other = (Topics) obj;
		if (edate == null) {
			if (other.edate != null)
				return false;
		} else if (!edate.equals(other.edate))
			return false;
		if (sdate == null) {
			if (other.sdate != null)
				return false;
		} else if (!sdate.equals(other.sdate))
			return false;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		if (tname == null) {
			if (other.tname != null)
				return false;
		} else if (!tname.equals(other.tname))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		if (usid == null) {
			if (other.usid != null)
				return false;
		} else if (!usid.equals(other.usid))
			return false;
		if (usids == null) {
			if (other.usids != null)
				return false;
		} else if (!usids.equals(other.usids))
			return false;
		return true;
	}
}
