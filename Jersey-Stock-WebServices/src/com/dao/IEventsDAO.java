package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.vo.Events;
import com.dbc.DatabaseConnection;
/** The random events logic class     
* @author Chengjiao Yang  
*/  
public class IEventsDAO {
	private DatabaseConnection dbc = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public IEventsDAO(){// instance class
		try {
			dbc = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.conn = this.dbc.getConnection();
	}
	
	/** This function is to get the random event 
	  * @param null  
	  * @return Events VO
	  * @exception exceptions database exceptions
	  */ 
	public Events getRandomEvent() throws Exception {
		Events event = new Events();
		try {
			String sql = "SELECT eventID,incident,Variation_Range FROM events order by rand() limit 1";
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();// get the result set
			if (rs.next()) {
				event.seteventID(rs.getInt("eventID"));
				event.setincident(rs.getString("incident"));
				event.setVariation_Range(rs.getDouble("Variation_Range"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.pstmt.close();
			this.dbc.close();
		}
		return event;
	}
}
