package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vo.*;
import com.dbc.DatabaseConnection;
import com.factory.DAOFactory;

public class IHoldDAO {
	private DatabaseConnection dbc = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public IHoldDAO(){// instance class
		try {
			dbc = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.conn = this.dbc.getConnection();
	}
	
	public List<HoldCompany> getAllHoldById(HoldCompany hold) throws Exception {
		List<HoldCompany> list = new ArrayList<HoldCompany>();
		try {
			String sql = "select c.name, a.shares, b.sid from (select sid, shares from hold where userid = ?) a, stock b, company c where b.cid = c.cid and a.sid = b.sid";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, hold.getuserID());
			ResultSet rs = this.pstmt.executeQuery();// get the result set
			while (rs.next()) {
				HoldCompany hc = new HoldCompany();
				hc.setuserID(hold.getSid());
				hc.setSid(rs.getString("sid"));
				hc.setshares(rs.getInt("shares"));
				hc.setName(rs.getString("name"));
				list.add(hc);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.pstmt.close();
			this.dbc.close();
		}
		return list;
	}
	
	public List<HoldCompany> getAllHoldById(String userid) throws Exception {
		List<HoldCompany> list = new ArrayList<HoldCompany>();
		try {
			String sql = "select c.name, a.shares, b.sid from (select sid, shares from hold where userid = ?) a, stock b, company c where b.cid = c.cid and a.sid = b.sid";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, userid);
			ResultSet rs = this.pstmt.executeQuery();// get the result set
			while (rs.next()) {
				HoldCompany hc = new HoldCompany();
				hc.setuserID(userid);
				hc.setSid(rs.getString("sid"));
				hc.setshares(rs.getInt("shares"));
				hc.setName(rs.getString("name"));
				list.add(hc);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.pstmt.close();
			this.dbc.close();
		}
		return list;
	}
	
	public HoldCompany getSingleHoldById(String userid,String sid) throws Exception {
		HoldCompany hc = new HoldCompany();
		try {
			String sql = "select c.name, a.shares, b.sid from (select sid, shares from hold where userid = ? and sid = ?) a, stock b, company c where b.cid = c.cid and a.sid = b.sid";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, userid);
			this.pstmt.setString(2, sid);
			ResultSet rs = this.pstmt.executeQuery();// get the result set
			if (rs.next()) {
				hc.setuserID(userid);
				hc.setSid(rs.getString("sid"));
				hc.setshares(rs.getInt("shares"));
				hc.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.pstmt.close();
			this.dbc.close();
		}
		return hc;
	}
	
	/** This function allow the investor sell all the stock he/she hold
	  * @param investorID,stockID,stock_price
	  * @return true/false
	  * @throws Exception 
	  * @exception exceptions database exceptions
	  */ 
	public boolean sellALL(String investorID, String stockID, double stock_price) throws Exception {	
		double shares = getshares(investorID,stockID);
		if(shares<0) return false;
		try {
			//delete the data in hold
			String sql = "delete from hold where userID = ? and Sid = ?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, investorID);
			this.pstmt.setString(2, stockID);
			int rs = this.pstmt.executeUpdate();
			if(rs<1) return false;
			
			//calculate increase money and update the investor asset
			double money = shares * stock_price;
			return DAOFactory.getIInvestorDAOInstance().increaseMoney(investorID, money);
			
			//update the record(transaction record) table
		} catch (Exception e) {
			throw e;
		} finally {
			this.pstmt.close();
			this.dbc.close();
		}
	}
	/** This function allow the investor sell the amount of stock he/she hold
	  * @param String investorID, String stockID, double stock_price, int amount
	  * @return true/false
	  * @throws Exception 
	  * @exception exceptions database exceptions
	  */ 
	public boolean sellAmount(String investorID, String stockID, double stock_price, int amount) throws Exception {	
		double shares = getshares(investorID,stockID);
		if(shares == 0||shares<amount) return false;
		boolean isSellAll = shares == amount?true:false;
		try {
			if(isSellAll){
				//delete the data in hold
				String sql = "delete from hold where userID = ? and Sid = ?";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, investorID);
				this.pstmt.setString(2, stockID);
				int rs = this.pstmt.executeUpdate();
				if(rs<1) return false;
			}
			else{
				//update the data in hold
				String sql = "UPDATE hold set shares = shares-? where userID = ? and Sid = ?";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, amount);
				this.pstmt.setString(2, investorID);
				this.pstmt.setString(3, stockID);
				int rs = this.pstmt.executeUpdate();
				if(rs<1) return false;
			}
			
			//calculate increase money and update the investor asset
			double money = amount * stock_price;
			return DAOFactory.getIInvestorDAOInstance().increaseMoney(investorID, money);
			
			//update the record(transaction record) table
		} catch (Exception e) {
			throw e;
		} finally {
			this.pstmt.close();
			this.dbc.close();
		}
	}
	/** This function allow the investor to buy the amount stock, make sure the investor has enough money--
	  * @param String investorID, String stockID, double stock_price, int amount
	  * @return true/false
	  * @throws Exception 
	  * @exception exceptions database exceptions
	  */ 
	public boolean buyAmount(String investorID, String stockID, double stock_price, int amount) throws Exception {	
		double shares = getshares(investorID,stockID);
		boolean isNewStock = shares == 0?true:false;
		//see if investor has enough money
		double money_require = amount * stock_price;
		Investors investor = new Investors();
		investor.setuserID(investorID);
		investor = DAOFactory.getIInvestorDAOInstance().getInvestorById(investor);
		if(investor.getAssets()<money_require) return false;
		try {
			if(isNewStock){ 
				//insert the new relation of investor and stock
				String sql = "insert into hold values(?,?,?)";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, investorID);
				this.pstmt.setString(2, stockID);
				this.pstmt.setInt(3, amount);
				int rs = this.pstmt.executeUpdate();
				if(rs<1) return false;
			}
			else{
				//update the data in hold
				String sql = "UPDATE hold set shares = shares+? where userID = ? and Sid = ?";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, amount);
				this.pstmt.setString(2, investorID);
				this.pstmt.setString(3, stockID);
				int rs = this.pstmt.executeUpdate();
				if(rs<1) return false;
			}
			//calculate increase money and update the investor asset
			return DAOFactory.getIInvestorDAOInstance().increaseMoney(investorID, -money_require);
			
			//update the record(transaction record) table
		} catch (Exception e) {
			throw e;
		} finally {
			this.pstmt.close();
			this.dbc.close();
		}
	}
	/** This function is to get the shares of the particular investor and stock
	  * @param String investorID, String stockID
	  * @return double shares
	  * @throws Exception 
	  * @exception exceptions database exceptions
	  */ 
	public int getshares(String investorID, String stockID) throws Exception{
		int shares = 0;
		try {
			String sql = "SELECT shares FROM hold where userID = ? and Sid = ?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, investorID);
			this.pstmt.setString(2, stockID);
			ResultSet rs = this.pstmt.executeQuery();// get the result set
			if (rs.next()) {
				shares = rs.getInt("shares");
			}
		} catch (Exception e) {
			throw e;
		}
		return shares;
	}
}
