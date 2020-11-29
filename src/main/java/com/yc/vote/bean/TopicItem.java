package com.yc.vote.bean;

import java.io.Serializable;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年9月3日
 * Email haijunzhou@hnit.edu.cn
 */
public class TopicItem implements Serializable{
	private static final long serialVersionUID = 7138042168792523958L;
	private Integer ino;
	private String tid;
	private String iname;
	private Integer nums;
	
	@Override
	public String toString() {
		return "TopicItem [ino=" + ino + ", tid=" + tid + ", iname=" + iname + ", nums=" + nums + "]";
	}

	public Integer getIno() {
		return ino;
	}

	public void setIno(Integer ino) {
		this.ino = ino;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iname == null) ? 0 : iname.hashCode());
		result = prime * result + ((ino == null) ? 0 : ino.hashCode());
		result = prime * result + ((nums == null) ? 0 : nums.hashCode());
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
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
		TopicItem other = (TopicItem) obj;
		if (iname == null) {
			if (other.iname != null)
				return false;
		} else if (!iname.equals(other.iname))
			return false;
		if (ino == null) {
			if (other.ino != null)
				return false;
		} else if (!ino.equals(other.ino))
			return false;
		if (nums == null) {
			if (other.nums != null)
				return false;
		} else if (!nums.equals(other.nums))
			return false;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		return true;
	}
}
