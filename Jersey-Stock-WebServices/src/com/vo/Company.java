package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

//table_company create table company(Cid char(50), MarketValuation real);

@XmlRootElement
public class Company {
	private String Cid; //��˾id
	private String Name; //��˾����
	private Double MarketValuation; //��˾��ֵ

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
