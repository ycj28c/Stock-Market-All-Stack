package com.clientExample;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.GenericType;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//
//
/* MAVEN setting
 *   <dependencies>
  	<dependency>
  		<groupId>org.glassfish.jersey.core</groupId>
  		<artifactId>jersey-client</artifactId>
  		<version>2.13</version>
  	</dependency>
  	<dependency>
    <groupId>org.glassfish.jersey.connectors</groupId>
    <artifactId>jersey-grizzly-connector</artifactId>
    <version>2.13</version>
</dependency>
 
<dependency>
    <groupId>org.glassfish.jersey.connectors</groupId>
    <artifactId>jersey-apache-connector</artifactId>
    <version>2.13</version>
</dependency>
 
<dependency>
    <groupId>org.glassfish.jersey.connectors</groupId>
    <artifactId>jersey-jetty-connector</artifactId>
    <version>2.13</version>
</dependency>
<dependency>
<groupId>org.codehaus.jackson</groupId>
<artifactId>jackson-core-asl</artifactId>
<version>1.9.12</version>
</dependency>
<dependency>
<groupId>org.codehaus.jackson</groupId>
<artifactId>jackson-mapper-asl</artifactId>
<version>1.9.12</version>
</dependency>
<dependency>
<groupId>org.codehaus.jackson</groupId>
<artifactId>jackson-jaxrs</artifactId>
<version>1.9.12</version>
</dependency>
<dependency>
<groupId>javax.servlet</groupId>
<artifactId>servlet-api</artifactId>
<version>2.5</version>
<scope>provided</scope>
</dependency>
  </dependencies>
 * 
 */
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.vo.*;

public class client {
/*
	//客户端获取stocks及得到泛型的例子，get
	public static void getStocks() {
		String serverURI = "http://127.0.0.1:8080/WebServices/stocks";
		Client client = ClientBuilder.newClient();
		//client.register(JacksonJsonProvider.class);
		WebTarget target = client.target(serverURI);
		Response response = target.request().get();
		//泛型的传递不好用
		//List<StockCompany> l = response.readEntity(new GenericType<List<StockCompany>>(){});
		//System.out.println("看看泛型："+l.get(0).getName());
		//泛型的传递
		//String res = response.readEntity(String.class);
		//用text/plain传递gson转换的string，在用gson转换回来
		String res = response.readEntity(String.class);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		java.lang.reflect.Type collectionType = new com.google.gson.reflect.TypeToken<List<StockCompany>>(){}.getType();  
		List<StockCompany> l = gson.fromJson(res, collectionType);
		//System.out.println(res);
		System.out.println("看看泛型："+l.get(3).getName());
		response.close();
	}
	//客户端获得account信息及单对象的例子，get
	public static void getUserById() {
		String serverURI = "http://127.0.0.1:8080/WebServices/1/account";
		Client client = ClientBuilder.newClient();
		//client.register(JacksonJsonProvider.class);
		WebTarget target = client.target(serverURI);
		Response response = target.request().get();
		//单个bean的传递
		String res = response.readEntity(String.class);  
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Investors sc = gson.fromJson(res, Investors.class);
		//Investors sc = response.readEntity(Investors.class);
		System.out.println("姓名："+sc.getuserName()+" ID:"+sc.getuserID()+" 财产："+sc.getAssets());
		response.close();
	}
	//购买新股(如果已经有了就再购买)，更新的例子，post
	public static void buyNewStock() {
		String sid = "000009";
		int shares = 100;
		String userid = "1";
		String serverURI = "http://127.0.0.1:8080/WebServices/"+userid+"/hold/"+sid;
		
		Hold hold = new Hold();
		hold.setshares(shares);
		hold.setSid(sid);
		hold.setuserID(userid);
		
		Client client = ClientBuilder.newClient();
		client.register(JacksonJsonProvider.class);//如果发送json必须加上这行
		WebTarget target = client.target(serverURI);
		Response response = target.request().buildPost(Entity.entity(hold, MediaType.APPLICATION_JSON)).invoke();
		
		//单例转换
		String res = response.readEntity(String.class);  
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HoldCompany hc = gson.fromJson(res, HoldCompany.class);
		System.out.println("股票名:"+hc.getName()+" 股票数："+hc.getshares());
		response.close();
	}
	//更新用户信息，更新的例子，put
	public static void updateInvestor() {
		String sid = "000002";
		int shares = 100;
		String userid = "2";
		String serverURI = "http://127.0.0.1:8080/WebServices/"+userid+"/account";
		
		Investors investor = new Investors();
		investor.setuserID("2");
		investor.setpassword("111");
		investor.setsex("female");
		investor.setAssets(40001.0);
		investor.setAmountofShares(555554);
		investor.setuserName("lucy");
		
		//发送
		Client client = ClientBuilder.newClient();
		client.register(JacksonJsonProvider.class); //如果发送json必须加上这行
		WebTarget target = client.target(serverURI);
		Response response = target.request().buildPut(Entity.entity(investor, MediaType.APPLICATION_JSON)).invoke();
		
		//接收
		String res = response.readEntity(String.class);
		System.out.println(res);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Investors inves = gson.fromJson(res, Investors.class);
		System.out.println(inves.getuserName());
		response.close();
	}
	//删除一个hold，删除的例子，delete
	public static void deleteHoldAmount() {
		String serverURI = "http://127.0.0.1:8080/WebServices/1/hold/000008";
		Client client = ClientBuilder.newClient();
		//client.register(JacksonJsonProvider.class); 
		WebTarget target = client.target(serverURI);
		Response response = target.request().delete();
		response.close();
	}
	*/
	public static void main(String[] args) {
		/*try {
    		String targetURL = "http://127.0.0.1:8080/WebServices/xxdd/getJson";
            URL restServiceURL = new URL(targetURL);
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");
            if (httpConnection.getResponseCode() != 200) {
            	throw new RuntimeException("HTTP GET Request Failed with Error code : " + httpConnection.getResponseCode());
            }
            
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));

            String output;
            System.out.println("Output from Server:  \n");
            while ((output = responseBuffer.readLine()) != null) {
            	System.out.println(output);
            }
            httpConnection.disconnect();
       } catch (MalformedURLException e) {
            e.printStackTrace();
       } catch (IOException e) {
            e.printStackTrace();
       }	
	*/
	}
	

}
