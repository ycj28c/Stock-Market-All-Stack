package com.dao;

import java.sql.*;
import com.vo.*;
import com.dbc.DatabaseConnection;

public class ICompanyDAO {
	private DatabaseConnection dbc = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public ICompanyDAO(){// instance class
		try {
			dbc = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.conn = this.dbc.getConnection();
	}
	
	public boolean testMethod(Company company) throws Exception {
		boolean flag = false;
		try {
			String sql = "select Cid, MarketValuation from company where Cid=? and MarketValuation=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, company.getCid());// set string
			this.pstmt.setDouble(2, company.getMarketValuation());// set double
			ResultSet rs = this.pstmt.executeQuery();// get the result set
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.pstmt.close();
			this.dbc.close();
		}
		return flag;
	}
}
