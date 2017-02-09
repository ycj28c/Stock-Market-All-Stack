cs542HtmlCorsClient
===================

CORS WebService Client

+ It connect to the local WebService for test, to call remote host, just change all the http://localhost:8080 in index.html

+ Use CORS technology, the Jersey WebService also have to setting fiter to accept the http request

```
@Provider
public class AccessControlResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        final MultivaluedMap<String,Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Origin, Accept, authorization, X-Requested-With, X-Codingpedia");
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
```

And implement it to the applicaion setting

```
@ApplicationPath("/")
public class DBMS extends ResourceConfig {

	public DBMS() {
		.......
		//CORS, add the line below
		register(AccessControlResponseFilter.class);
	}
```
}

+ One thing waste me lots of time is

```
   var jsonMsg = {'userID':'1','Sid':sid,'shares':amount}; 
```
   here we can't use upper letter "Sid", can only use lower letter "sid", like below
   
```
   var jsonMsg = {'userID':'1','sid':sid,'shares':amount}; 
```

+ something the browse will change the POST and PUT request to OPTIONS request, make sure you have set the right ajax request, like

```
function buyStock(sid,amount){
	var jsonMsg = {'userID':'1','sid':sid,'shares':amount}; 
	
	var message = JSON.stringify(jsonMsg);
	
	$.ajax({
	
　　　　type: "post",
    
　　　　url: "http://localhost:8080/WebServices/1/hold/"+sid,
    
　　　　contentType: "application/json",
    
　　　　data: message,
    
       crossDomain: true,
       
　　　　dataType: "json",
    
　　　　processData: false,
    
　　　　success: function (json) { alert("buy stock success!"); },
		
　　　　error: function (error) { alert("ERROR" + error.responseText); }
	});
}
```

+ support google chrome, didn't test other browse
