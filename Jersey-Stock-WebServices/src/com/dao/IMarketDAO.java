package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dbc.DatabaseConnection;
import com.vo.Events;
import com.vo.Market;
/** The Overall Market logic class     
* @author Chengjiao Yang  
*/  
public class IMarketDAO {
	private DatabaseConnection dbc = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public IMarketDAO(){// instance class
		try {
			dbc = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.conn = this.dbc.getConnection();
	}
	
	/** This function is to get the Overall Market Information 
	  * @param null  
	  * @return Market VO
	  * @exception exceptions database exceptions
	  */ 
	public Market getMarket() throws Exception {
		Market market = new Market();
		try {
			String sql = "select marketIndex,VariationRange,OverallCapital from market";
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();// get the result set
			if (rs.next()) {
				market.setMarketIndex(rs.getInt("marketIndex"));
				market.setVariationRange(rs.getDouble("VariationRange"));
				market.setOverallCapital(rs.getDouble("OverallCapital"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.pstmt.close();
			this.dbc.close();
		}
		return market;
	}
	/** This function is to get the Overall Market Information after effected by event
	  * @param Events  
	  * @return Market VO
	 * @throws Exception 
	  * @exception exceptions database exceptions
	  */ 
	public Market getMarketAfterEvent(Events event) throws Exception{
		double range = event.getVariation_Range();
		
		Market market = getMarket();
		double randomVarible = 1-Math.random()*2;//random range from -1 to +1
		double offset = randomVarible * 0.03;
		//double variation_range = randomVarible*market.getVariationRange();
		//double variation_after_event = variation_range + range*Math.abs(variation_range);
		
		double variation_after_event = offset + range;
		if(variation_after_event>Math.abs(market.getVariationRange())){ //variation are limited in VariationRange range
			variation_after_event = Math.abs(market.getVariationRange());
		}
		else if(variation_after_event<-Math.abs(market.getVariationRange())){
			variation_after_event = -Math.abs(market.getVariationRange());
		}
		
		int marketIndex_after_event = (int)(market.getMarketIndex()+ market.getMarketIndex()*variation_after_event);
		variation_after_event = (Math.round(variation_after_event*10000))/10000.0; //4 digits
		
		market.setMarketIndex(marketIndex_after_event);
		market.setVariationRange(variation_after_event);
		
		return market;
	}
}
