package com.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transactionrecord {
	private String TID;
	private String userID;
	private String Cid;
	private Date time;
	private Double price;

	public String getTID() {
		return TID;
	}

	public void setTID(String TID) {
		this.TID = TID;
	}

	public String getuserID() {
		return userID;
	}

	public void setuserID(String userID) {
		this.userID = userID;
	}

	public String getCid() {
		return Cid;
	}

	public void setCid(String Cid) {
		this.Cid = Cid;
	}

	public Date gettime() {
		return time;
	}

	public void settime(Date time) {
		this.time = time;
	}

	public Double getprice() {
		return price;
	}

	public void setprice(Double price) {
		this.price = price;
	}
}
