package com.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.test.TestBean;

@Path("/")
public class TestResource {
	@Path("getText")
	@GET
	@Produces("text/plain")
	public String getText() {
		return "hello lucky";
	}

	@Path("getXml")
	@GET
	@Produces(value = MediaType.APPLICATION_XML)
	public TestBean getXml() {
		return new TestBean("a", 26, 62);
	}

	@Path("getJson")
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public TestBean getJson() {
		return new TestBean("a", 26, 62);
	}
}