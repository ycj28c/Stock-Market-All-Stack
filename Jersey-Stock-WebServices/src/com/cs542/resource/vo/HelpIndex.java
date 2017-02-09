package com.cs542.resource.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.factory.TOOLFactory;

@XmlRootElement
public class HelpIndex {
	public String market_url;  //大盘url
	public String stocks_url;  //全股票url
	public String stock_url;  //个股url
	public String events_url; //事件url
	public String events_refresh_url; //刷新事件url
	public String users_url;  //用户url
	public String users_hold_url;  //用户所持股票url
	public String users_hold_stock_url;  //用户所持股票个股url
	public String users_account_url; //用户信息url
	//public String help_url; //帮助url
	public String Version = "1.0"; //版本号
	public String Course_project = "CS542";
	public String Author = "Chengjiao Yang"; //作者
	public String Create_Time = "12/02/2014"; //创建时间
	public String Course_name = "Database Management Systems Course";
	

	public HelpIndex(){
		String ip = TOOLFactory.getnetworkToolInstance().getLocalIP();
		this.market_url = "http://"+ip+":8080/WebServices/market";
		this.stocks_url = "http://"+ip+":8080/WebServices/stocks";
		this.stock_url = "http://"+ip+":8080/WebServices/stocks/{stockid}";
		this.events_url = "http://"+ip+":8080/WebServices/events";
		this.events_refresh_url = "http://"+ip+":8080/WebServices/events/refresh";
		this.users_url = "http://"+ip+":8080/WebServices/{userid}";
		this.users_hold_url = "http://"+ip+":8080/WebServices/{userid}/hold";
		this.users_hold_stock_url = "http://"+ip+":8080/WebServices/{userid}/hold/{stockid}";
		this.users_account_url = "http://"+ip+":8080/WebServices/{userid}/account";
		//this.help_url = "http://"+ip+":8080/WebServices/help";
	}


}
