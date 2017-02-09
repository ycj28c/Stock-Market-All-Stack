package com.cs542.resource.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.factory.TOOLFactory;

@XmlRootElement
public class HelpIndex {
	public String market_url;  //����url
	public String stocks_url;  //ȫ��Ʊurl
	public String stock_url;  //����url
	public String events_url; //�¼�url
	public String events_refresh_url; //ˢ���¼�url
	public String users_url;  //�û�url
	public String users_hold_url;  //�û����ֹ�Ʊurl
	public String users_hold_stock_url;  //�û����ֹ�Ʊ����url
	public String users_account_url; //�û���Ϣurl
	//public String help_url; //����url
	public String Version = "1.0"; //�汾��
	public String Course_project = "CS542";
	public String Author = "Chengjiao Yang"; //����
	public String Create_Time = "12/02/2014"; //����ʱ��
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
