package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StockCompany {
	private String Sid; //股票id
	private Double Price_share; //股票单价
	private Double Variation_Range; //浮动量
	private String Name; //公司名称
	
	public String getSid() {
		return Sid;
	}

	public void setSid(String Sid) {
		this.Sid = Sid;
	}
	
	public Double getPrice_share() {
		return Price_share;
	}

	public void setPrice_share(Double Price_share) {
		this.Price_share = Price_share;
	}
	
	public Double getVariation_Range() {
		return Variation_Range;
	}

	public void setVariation_Range(Double Variation_Range) {
		this.Variation_Range = Variation_Range;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
}
