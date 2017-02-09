package com.cs542;

import java.util.List;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
//import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.factory.DAOFactory;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fiter.AccessControlResponseFilter;
import com.vo.Events;
import com.vo.GlobalVo;
import com.vo.Market;
import com.vo.StockCompany;

@ApplicationPath("/")
public class DBMS extends ResourceConfig {

	public DBMS() throws Exception {
		//���������ڵİ�·�� 
		packages("com.cs542.resource");
		//��ӡ������־�����ڸ��ٵ��ԣ���ʽ���������
		register(LoggingFilter.class);
		//ע��JSONת���� (�������ֶ����ԣ�����tomcat���������⣬��Ҫʹ��glassfish4����)
		//register(JacksonFeature.class);
		register(JacksonJsonProvider.class); 
		//CORS���ˣ������������
		register(AccessControlResponseFilter.class);
		
		setENV();	
	}
	//set the initial data for globalVO
	@SuppressWarnings("static-access")
	public void setENV() throws Exception{
		GlobalVo globalvo = new GlobalVo();
		Events event = DAOFactory.getIEventsInstance().getRandomEvent();
		Market market = DAOFactory.getIMarketDAOInstance().getMarketAfterEvent(event);
		List<StockCompany> stocks = DAOFactory.getIStockDAOInstance().getAllStocksAfterEvent(event);
		
		globalvo.event = event;
		globalvo.market = market;
		globalvo.stocks = stocks;
	}
}