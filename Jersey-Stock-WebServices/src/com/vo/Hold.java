package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hold {
	private String userID;
	private String Sid;
	private int shares;

	public String getuserID() {
		return userID;
	}

	public void setuserID(String userID) {
		this.userID = userID;
	}

	public String getSid() {
		return Sid;
	}

	public void setSid(String Sid) {
		this.Sid = Sid;
	}

	public int getshares() {
		return shares;
	}

	public void setshares(int shares) {
		this.shares = shares;
	}
}
