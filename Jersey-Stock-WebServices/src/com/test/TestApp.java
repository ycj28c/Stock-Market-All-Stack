package com.test;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
//import org.glassfish.jersey.jackson.JacksonFeature;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@ApplicationPath("/test")
public class TestApp extends ResourceConfig {

	public TestApp() {
		//服务类所在的包路径 
		packages("com.test");
		//打印访问日志，便于跟踪调试，正式发布可清除
		register(LoggingFilter.class);
		//注册JSON转换器 (以下两种都可以，不过tomcat部署有问题，需要使用glassfish4以上)
		//register(JacksonFeature.class);
		register(JacksonJsonProvider.class); 
	}

}