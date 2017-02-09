package com.cs542.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.factory.TOOLFactory;
import com.vo.GlobalVo;
import com.vo.StockCompany;

@Path("/stocks")
@SuppressWarnings("static-access")
public class stockResource {
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	//@Produces("text/plain")
	public String getStock(){
		//Events event = new Events();//全局变量的event
		//event.seteventID(1);
		//System.out.println(event.getincident());
		//List<StockCompany> list = DAOFactory.getIStockDAOInstance().getAllStocksAfterEvent(event);
		
		GlobalVo globalvo = new GlobalVo();
		List<StockCompany> stocks = globalvo.stocks;
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(stocks);
		return output;
	}
	
	//获取某只股票，需要修改为按照event变化
	@Path("{sid}")
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	//@Produces("text/plain")
	public String getStockBySid(@PathParam("sid") String sid){
		//Events event = new Events();//全局变量的event
		//StockCompany sc = DAOFactory.getIStockDAOInstance().getSingleStocksAfterEvent(sid, event);
		
		GlobalVo globalvo = new GlobalVo();
		List<StockCompany> stocks = globalvo.stocks;
		StockCompany sc = new StockCompany();
		for(StockCompany temp:stocks){
			if(temp.getSid().equals(sid)){
				//sc.setName(temp.getName());
				//sc.setPrice_share(temp.getPrice_share());
				//sc.setSid(temp.getSid());
				//sc.setVariation_Range(temp.getVariation_Range());
				sc = temp;
			}
		}
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(sc);
		return output;
	}
}
