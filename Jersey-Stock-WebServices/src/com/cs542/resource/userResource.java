package com.cs542.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import com.cs542.resource.vo.UserList;
import com.factory.TOOLFactory;

@Path("/{userid}")
public class userResource {
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public String getUserList(@PathParam("userid") String userid) throws JSONException{
		/*
		//自己创建json的方法
		JSONObject userlist = new JSONObject();
		try {
			userlist.put("Your Account:", "http://{server_address}/{user}/account");
			userlist.put("Your Hold", "http://{server_address}/{user}/hold");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userlist;
		*/
		
		//转换为好看的json
		/*String jsonOutput = null;
		try{
			Gson gson = new GsonBuilder().setPrettyPrinting().create();    
			jsonOutput = gson.toJson(new UserList(userid)); 
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		String Output = TOOLFactory.getjsonToolInstance().prettyJSON(new UserList(userid));
        return Output;
	}
	
	/***********************************************
	@Path("getText")
	@GET
	@Produces("text/plain")
	public String getText(@PathParam("userid") String userid) {
		return "hello lucky: "+userid;
	}

	@Path("getXml")
	@GET
	@Produces(value = MediaType.APPLICATION_XML)
	public TestBean getXml(@PathParam("userid") String userid) {
		return new TestBean(userid, 26, 62);
	}

	@Path("getJson")
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public List<StockCompany> getJson(@PathParam("userid") String userid) throws Exception{
//		Investors investor = new Investors();
//		investor.setuserID("1");
//		Investors sc = DAOFactory.getIInvestorDAOInstance().getInvestorById(investor);
//		return sc;
		List<StockCompany> sc = DAOFactory.getIStockDAOInstance().getAllStocks();
		return sc;
	}

	@Path("getJson")
	@DELETE
	@Produces(value = MediaType.APPLICATION_JSON)
	public List<StockCompany>  getJsonDel(@PathParam("userid") String userid) throws Exception {
		List<StockCompany> sc = DAOFactory.getIStockDAOInstance().getAllStocks();
		//System.out.println(sc.getSid());
		//Stock sc = DAOFactory.getIStockDAOInstance().getStockByID("000001");
		return sc;
		//return new Stock("000001",0.1,100,0.2,"haha");
	}
	@Path("getJson")
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	public void getJsonPut(@PathParam("userid") String userid) throws Exception {
		Hold hold = new Hold();
		String stockID = hold.getSid();
		String investorID = hold.getuserID();
		Stock stock = DAOFactory.getIStockDAOInstance().getStockByID(stockID);
		double stock_price = stock.getPrice_share();
		int amount = hold.getshares();
		DAOFactory.getIHoldDAOInstance().buyAmount(investorID, stockID, stock_price, amount);
		return;
	}
	@Path("getJson")
	@POST
	@Produces(value = MediaType.APPLICATION_JSON)
	public TestBean getJsonPost(@PathParam("userid") String userid) {
		return new TestBean(userid, 56, 65);
	}
	****************************************************/
}