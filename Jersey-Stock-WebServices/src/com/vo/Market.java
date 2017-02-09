package com.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Market {
	private int MarketIndex;
	private Double VariationRange;
	private Double OverallCapital;

	public int getMarketIndex() {
		return MarketIndex;
	}

	public void setMarketIndex(int MarketIndex) {
		this.MarketIndex = MarketIndex;
	}

	public Double getVariationRange() {
		return VariationRange;
	}

	public void setVariationRange(Double VariationRange) {
		this.VariationRange = VariationRange;
	}

	public Double getOverallCapital() {
		return OverallCapital;
	}

	public void setOverallCapital(Double OverallCapital) {
		this.OverallCapital = OverallCapital;
	}
}
