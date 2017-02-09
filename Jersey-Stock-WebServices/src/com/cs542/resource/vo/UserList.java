package com.cs542.resource.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.factory.TOOLFactory;

@XmlRootElement
public class UserList {
	public String account_url;
	public String hold_url;

	public UserList() {
	}

	public UserList(String userid){
		String ip = TOOLFactory.getnetworkToolInstance().getLocalIP();
		this.account_url = "http://"+ip+":8080/WebServices/" + userid + "/account";
		this.hold_url = "http://"+ip+":8080/WebServices/" + userid + "/hold";
	}
}
