package com.cs542.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cs542.resource.vo.HelpIndex;
import com.factory.TOOLFactory;


//这是帮助索引页，初始页面
@Path("/")
public class indexResource {

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public String getHelpIndex(){
		return TOOLFactory.getjsonToolInstance().prettyJSON(new HelpIndex());
	}
}
