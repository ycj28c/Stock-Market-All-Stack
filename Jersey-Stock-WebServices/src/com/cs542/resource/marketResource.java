package com.cs542.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.factory.TOOLFactory;
import com.vo.GlobalVo;
import com.vo.Market;

//大盘restful
@Path("/market")
public class marketResource {
	
	//@Path("getJson")
	@SuppressWarnings("static-access")
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public String getMarket() throws Exception {
		//Events event = new Events();//全局变量的event
		//System.out.println(event.getincident());
		//Market market = DAOFactory.getIMarketDAOInstance().getMarketAfterEvent(event);
		
		GlobalVo globalvo = new GlobalVo();
		Market market = globalvo.market;
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(market);
		return output;
	}

}
