package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

//table_company create table company(Cid char(50), MarketValuation real);

@XmlRootElement
public class Company {
	private String Cid; //公司id
	private String Name; //公司名称
	private Double MarketValuation; //公司市值

	public String getCid() {
		return Cid;
	}

	public void setCid(String Cid) {
		this.Cid = Cid;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public Double getMarketValuation() {
		return MarketValuation;
	}

	public void setMarketValuation(Double MarketValuation) {
		this.MarketValuation = MarketValuation;
	}
}
