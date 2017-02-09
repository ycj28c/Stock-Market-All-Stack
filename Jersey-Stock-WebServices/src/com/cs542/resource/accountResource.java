package com.cs542.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.factory.DAOFactory;
import com.factory.TOOLFactory;
import com.vo.Investors;

@Path("/{userid}/account")
public class accountResource {

	//获取某用户的账户信息
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public String getUserAccount(@PathParam("userid") String userid) throws Exception {
		Investors investor = DAOFactory.getIInvestorDAOInstance().getInvestorById(userid);
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(investor);
		return output;
	}
	
	//更新某用户的账户信息
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public String updateUserAccount(Investors investor) throws Exception {
		boolean isSuccess = DAOFactory.getIInvestorDAOInstance().UpdateInvestor(investor);
		Investors new_investor = new Investors();
		if(isSuccess){
			new_investor = DAOFactory.getIInvestorDAOInstance().getInvestorById(investor);
		}
		String output = TOOLFactory.getjsonToolInstance().prettyJSON(new_investor);
		return output;
	}
}
