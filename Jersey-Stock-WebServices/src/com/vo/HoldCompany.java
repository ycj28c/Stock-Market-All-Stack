package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

//personal stock vo
@XmlRootElement
public class HoldCompany { 
	private String userID; //用户id
	private String Sid; //股票id
	private int shares; //持有量
	private String Name; //公司名称
	
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
	
	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
	
	public int getshares() {
		return shares;
	}

	public void setshares(int shares) {
		this.shares = shares;
	}
}
