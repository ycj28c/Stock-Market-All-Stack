package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Investors {
	private String userID; // �û��˺�
	private String userName; //�û���
	private String password; // ����
	private String sex; // �Ա�
	private Double Assets; // �ܲƲ�
	private int AmountofShares; // ���е����й�Ʊ��

	public String getuserID() {
		return userID;
	}

	public void setuserID(String userID) {
		this.userID = userID;
	}
	
	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public Double getAssets() {
		return Assets;
	}
	
	public String getsex() {
		return sex;
	}

	public void setsex(String sex) {
		this.sex = sex;
	}

	public void setAssets(Double Assets) {
		this.Assets = Assets;
	}

	public int getAmountofShares() {
		return AmountofShares;
	}

	public void setAmountofShares(int AmountofShares) {
		this.AmountofShares = AmountofShares;
	}
}
