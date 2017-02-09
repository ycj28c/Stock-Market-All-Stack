package com.factory;

import com.dao.*;

public class DAOFactory {
	public static ICompanyDAO getICompanyDAOInstance() {// get Dao instance
		return new ICompanyDAO();
	}
	public static IHoldDAO getIHoldDAOInstance() {
		return new IHoldDAO();
	}
	public static IInvestorsDAO getIInvestorDAOInstance() {
		return new IInvestorsDAO();
	}
	public static IStockDAO getIStockDAOInstance() {
		return new IStockDAO();
	}
	public static IMarketDAO getIMarketDAOInstance() {
		return new IMarketDAO();
	}
	public static IEventsDAO getIEventsInstance() {
		return new IEventsDAO();
	}
}
