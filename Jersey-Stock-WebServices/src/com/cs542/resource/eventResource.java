package com.cs542.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.factory.DAOFactory;
import com.factory.TOOLFactory;
import com.vo.Events;
import com.vo.GlobalVo;
import com.vo.Market;
import com.vo.StockCompany;

@Path("/events")
@SuppressWarnings("static-access")
public class eventResource {


	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public String getEvent() throws Exception {
		//DAOFactory.getIEventsInstance().getRandomEvent();
		Events event = new GlobalVo().event;
		//System.out.println(event.getincident());
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(event);
		return output;
	}
	
	//这个用来刷新event，同时也计算并刷新mareket，stock
	//记录到全局变量的globalvo里面，这个vo包括event，market，stock，全部存在内存
	@Path("refresh")
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public String refreshEvent() throws Exception {
		GlobalVo globalvo = new GlobalVo();
		Events event = DAOFactory.getIEventsInstance().getRandomEvent();
		Market market = DAOFactory.getIMarketDAOInstance().getMarketAfterEvent(event);
		List<StockCompany> stocks = DAOFactory.getIStockDAOInstance().getAllStocksAfterEvent(event);
		
		globalvo.event = event;
		globalvo.market = market;
		globalvo.stocks = stocks;

		String output = TOOLFactory.getjsonToolInstance().prettyJSON(event);
		return output;
	}

}
