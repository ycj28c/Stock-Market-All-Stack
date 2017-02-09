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
		//服务类所在的包路径 
		packages("com.cs542.resource");
		//打印访问日志，便于跟踪调试，正式发布可清除
		register(LoggingFilter.class);
		//注册JSON转换器 (以下两种都可以，不过tomcat部署有问题，需要使用glassfish4以上)
		//register(JacksonFeature.class);
		register(JacksonJsonProvider.class); 
		//CORS过滤，允许跨域请求
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