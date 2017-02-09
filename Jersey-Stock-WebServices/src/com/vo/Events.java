package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Events {
	//定义为全局变量，方便调用/取消，全部放到global vo里面
	//static private int eventID = 0;
	//static private String incident = "Nothing happened so far";
	//static private Double Variation_Range = 0.0;
	
	private int eventID;
	private String incident;
	private Double Variation_Range;
		
	public Events(){}
	
	public int geteventID() {
		return eventID;
	}

	public void seteventID(int eventID) {
		this.eventID = eventID;
	}
	
	public String getincident() {
		return incident;
	}

	public void setincident(String incident) {
		this.incident = incident;
	}
	
	public Double getVariation_Range() {
		return Variation_Range;
	}

	public void setVariation_Range(Double Variation_Range) {
		this.Variation_Range = Variation_Range;
	}
}
