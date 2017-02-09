package com.cs542.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.factory.DAOFactory;
import com.factory.TOOLFactory;
import com.vo.GlobalVo;
import com.vo.Hold;
import com.vo.HoldCompany;
import com.vo.StockCompany;

//�û����й�Ʊrest
@Path("/{userid}/hold")
@SuppressWarnings("static-access")
public class userHoldResource {

	//�鿴�����������й�Ʊ
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	//@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public String getUserHold(@PathParam("userid") String userid) throws Exception{
		List<HoldCompany> list = DAOFactory.getIHoldDAOInstance().getAllHoldById(userid);
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(list);
		return output;
	}
	
	//�鿴ĳֻ���й�Ʊ
	@Path("{sid}")
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	//@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public String getUserHold(@PathParam("userid") String userid,@PathParam("sid") String sid) throws Exception{
		HoldCompany hc = DAOFactory.getIHoldDAOInstance().getSingleHoldById(userid, sid);
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(hc);
		return output;
	}
	
	//�����¹�Ʊ
	@POST
	@Produces(value = MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public String buyNewStock(Hold hold) throws Exception {		
		String investorID = hold.getuserID();
		List<HoldCompany> list = DAOFactory.getIHoldDAOInstance().getAllHoldById(investorID);
			
		String stockID = hold.getSid();
		int amount = hold.getshares();
		
		//get the current stock price
		GlobalVo globalvo = new GlobalVo();
		List<StockCompany> stocks = globalvo.stocks;
		StockCompany sc = new StockCompany();
		for(StockCompany temp:stocks){
			if(temp.getSid().equals(stockID)){
				sc = temp;
			}
		}
		double stock_price = sc.getPrice_share();
			
		boolean isSuccess = DAOFactory.getIHoldDAOInstance().buyAmount(investorID, stockID, stock_price, amount);
		if(isSuccess){
			list = DAOFactory.getIHoldDAOInstance().getAllHoldById(investorID);
		}
		//���ת��Ϊstring�������Զ�ʶ�𰡰�����Ҫ��׼ȷ��jsonתbean�ķ���
		//��Ư�������webҳ�����Ҫת����Ϊstring�����ᰡ
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(list);
		return output;
	}
	
	//�����¹�Ʊ,��buyNewStock��һ����
	@Path("{sid}")
	@POST
	@Produces(value = MediaType.APPLICATION_JSON)
	//http://localhost:8080/WebServices/userid/sid?amount=5
	//@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public String buyNewStockWithSid(@PathParam("userid") String userid,@PathParam("sid") String sid, @QueryParam("amount") int amount) throws Exception {		
		//List<HoldCompany> list = DAOFactory.getIHoldDAOInstance().getAllHoldById(userid);
		//System.out.println("&&&&&&&&&&"+userid+","+sid+","+amount);
		//get the current stock price
		GlobalVo globalvo = new GlobalVo();
		List<StockCompany> stocks = globalvo.stocks;
		StockCompany sc = new StockCompany();
		for(StockCompany temp:stocks){
			if(temp.getSid().equals(sid)){
				sc = temp;
			}
		}
		double stock_price = sc.getPrice_share();	
		
		HoldCompany hc = new HoldCompany();
		boolean isSuccess = DAOFactory.getIHoldDAOInstance().buyAmount(userid, sid, stock_price, amount);
		if(isSuccess){
			hc = DAOFactory.getIHoldDAOInstance().getSingleHoldById(userid, sid);
		}
		//���ת��Ϊstring�������Զ�ʶ�𰡰�����Ҫ��׼ȷ��jsonתbean�ķ���
		//��Ư�������webҳ�����Ҫת����Ϊstring�����ᰡ
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(hc);
		return output;
	}
	
	//�����¹�Ʊ,buyNewStockWithSid������
	@Path("{sid}")
	@POST
	@Produces(value = MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public String buyNewStockWithSid(Hold hold) throws Exception {	
		//System.out.println("****************"+hold.getshares()+","+hold.getuserID()+","+hold.getSid());
		String investorID = hold.getuserID();			
		String stockID = hold.getSid();
		int amount = hold.getshares();
		
		//get the current stock price
		GlobalVo globalvo = new GlobalVo();
		List<StockCompany> stocks = globalvo.stocks;
		StockCompany sc = new StockCompany();
		for(StockCompany temp:stocks){
			if(temp.getSid().equals(stockID)){
				sc = temp;
			}
		}
		double stock_price = sc.getPrice_share();
		//double stock_price = 111.00;
		
		HoldCompany hc = new HoldCompany();
		boolean isSuccess = DAOFactory.getIHoldDAOInstance().buyAmount(investorID, stockID, stock_price, amount);
		//boolean isSuccess = true;
		if(isSuccess){
			hc = DAOFactory.getIHoldDAOInstance().getSingleHoldById(investorID, stockID);
		}
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(hc);
		return output;
	}

	/* option test
	@Path("{sid}")
	@OPTIONS
	@Produces(value = MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public String buyNewStockWithSidxx(Hold hold) throws Exception {	
		//get the current stock price
		GlobalVo globalvo = new GlobalVo();
		List<StockCompany> stocks = globalvo.stocks;
		StockCompany sc = new StockCompany();
		sc = stocks.get(0);
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(sc);
		return output;
	}*/
	
	//�����ó��й�Ʊ
	@Path("{sid}")
	@DELETE
	@Produces(value = MediaType.APPLICATION_JSON) 
	public String sellStockBySid(@PathParam("userid") String userid,@PathParam("sid") String sid) throws Exception {
		HoldCompany hc = DAOFactory.getIHoldDAOInstance().getSingleHoldById(userid, sid);
		
		//get the current stock price
		GlobalVo globalvo = new GlobalVo();
		List<StockCompany> stocks = globalvo.stocks;
		StockCompany sc = new StockCompany();
		for(StockCompany temp:stocks){
			if(temp.getSid().equals(sid)){
				sc = temp;
			}
		}
		double stock_price = sc.getPrice_share();
		
		boolean isSuccess = DAOFactory.getIHoldDAOInstance().sellALL(userid, sid, stock_price);
		//delete�Ķ��壬����ɹ�����һ���ն���
		if(isSuccess){
			hc = new HoldCompany();
		}
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(hc);
		return output;
	}
	
	//���������һЩ��Ʊ
	@Path("{sid}")
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	//http://localhost:8080/WebServices/userid/sid?amount=5
	public String updateStockWithSid(@PathParam("userid") String userid,@PathParam("sid") String sid, @QueryParam("amount") int amount) throws Exception {		
		//get the current stock price
		GlobalVo globalvo = new GlobalVo();
		List<StockCompany> stocks = globalvo.stocks;
		StockCompany sc = new StockCompany();
		for(StockCompany temp:stocks){
			if(temp.getSid().equals(sid)){
				sc = temp;
			}
		}
		double stock_price = sc.getPrice_share();
		
		//������hold�;�hold�Ƚϣ��������뻹������
		HoldCompany hc = new HoldCompany();
		boolean isSuccess = false;
		int shares = DAOFactory.getIHoldDAOInstance().getshares(userid,sid);
		if(shares==amount){
			isSuccess = true;
		}
		else if(shares>amount){
			isSuccess = DAOFactory.getIHoldDAOInstance().sellAmount(userid, sid, stock_price, shares-amount);
		}
		else{
			isSuccess = DAOFactory.getIHoldDAOInstance().buyAmount(userid, sid, stock_price, amount-shares);
		}
					
		if(isSuccess){
			hc = DAOFactory.getIHoldDAOInstance().getSingleHoldById(userid, sid);
		}
		//���ת��Ϊstring�������Զ�ʶ�𰡰�����Ҫ��׼ȷ��jsonתbean�ķ���
		//��Ư�������webҳ�����Ҫת����Ϊstring�����ᰡ
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(hc);
		return output;
	}
	
	//���������һЩ��Ʊ��updateStockWithSid����
	@Path("{sid}")
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public String updateStockWithSid(Hold hold) throws Exception {			
		String investorID = hold.getuserID();			
		String stockID = hold.getSid();
		int amount = hold.getshares();
		
		//get the current stock price
		GlobalVo globalvo = new GlobalVo();
		List<StockCompany> stocks = globalvo.stocks;
		StockCompany sc = new StockCompany();
		for(StockCompany temp:stocks){
			if(temp.getSid().equals(stockID)){
				sc = temp;
			}
		}
		double stock_price = sc.getPrice_share();
		//double stock_price = 111.00;
			
		//������hold�;�hold�Ƚϣ��������뻹������
		HoldCompany hc = new HoldCompany();
		boolean isSuccess = false;
		int shares = DAOFactory.getIHoldDAOInstance().getshares(investorID,stockID);
		if(shares==amount){
			isSuccess = true;
		}
		else if(shares>amount){
			isSuccess = DAOFactory.getIHoldDAOInstance().sellAmount(investorID, stockID, stock_price, shares-amount);
		}
		else{
			isSuccess = DAOFactory.getIHoldDAOInstance().buyAmount(investorID, stockID, stock_price, amount-shares);
		}
			
		if(isSuccess){
			hc = DAOFactory.getIHoldDAOInstance().getSingleHoldById(investorID, stockID);
		}
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(hc);
		return output;
	}
	
	/*******
	@Path("/{sid}")
	@PUT
	//@Produces(value = MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public String getJsonPut(Hold hold) throws Exception {
		System.out.println("----------------"+hold.getshares());
		String stockID = hold.getSid();
		System.out.println("||||||||||||||||||||||"+stockID);
		hold.setSid(stockID);
		String investorID = hold.getuserID();
		Stock stock = DAOFactory.getIStockDAOInstance().getStockByID(stockID);
		double stock_price = stock.getPrice_share();
		int amount = hold.getshares();
		DAOFactory.getIHoldDAOInstance().buyAmount(investorID, stockID, stock_price, amount);
		System.out.println("++++++++++++++++"+hold.getshares());
		return String.valueOf(hold.getshares());
	}
	
	@Path("/{sid}")
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public TestBean getJsonGET() {
		return new TestBean("haha", 88, 88);
	}
	********/
}
