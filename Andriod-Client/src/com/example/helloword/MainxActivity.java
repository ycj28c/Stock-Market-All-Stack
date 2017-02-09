package com.example.helloword;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainxActivity extends Activity implements OnClickListener{
	private final int SUCCESS = 0;  
    private final int FAILURE = 1; 
    private TextView textview;
    private Handler handler = new Handler() {  
		//TextView textview = (TextView) findViewById(R.id.textView2);
        public void handleMessage(Message msg) {  
            switch (msg.what) {  
	            // 获取成功  
	            case SUCCESS:  
	                //imageView.setImageBitmap((Bitmap) msg.obj); 
	            	textview.setText(msg.obj.toString());
	            	Log.i("mylog","sucess");
	                break;  
	            // 获取失败  
	            case FAILURE:  
	                //Toast.makeText(MainActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();  
	            	textview.setText("failure");
	            	Log.i("mylog","failure");
	                break;  
	            default:  
	                break;  
            }  
        };  
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainx);
		
		//final EditText testtx = (EditText)findViewById(R.id.editText1);
		final Button testbu = (Button)findViewById(R.id.button1); 
		testbu.setOnClickListener(this);
		
		final Button testbu2 = (Button)findViewById(R.id.button2); 
		testbu2.setOnClickListener(this);
		
		//testtx.setText("6178505822");
		textview = (TextView) findViewById(R.id.textView2);
		
		 // 为button注册事件 
		/*testbu.setOnClickListener(new Button.OnClickListener() { 
			public void onClick(View v) { 
				// 拨打电话，电话号码来自EditText中输入的内容 
				Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + testtx.getText())); 
				callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				        startActivity(callIntent); 
				} 
	    }); */
				
		//测试rest
		/*testbu2.setOnClickListener(new Button.OnClickListener() { 
			public void onClick(View view) {
				TextView tvResult = (TextView) findViewById(R.id.textView2);
				tvResult.setText("dfgdfgdfg");
		        try {
		        	HttpClient client = new DefaultHttpClient();
		        	tvResult.setText("1111");
		        	HttpGet httpGet = new HttpGet("https://api.github.com/");
		        	tvResult.setText("2222");
		        	HttpResponse response = client.execute(httpGet);
		        	tvResult.setText("3333");
		        	InputStream inputStream = response.getEntity().getContent();
		        	tvResult.setText("4444");
		          	StringBuffer buffer = new StringBuffer();
		           	BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		          	String str = new String("");
		        	while ((str = bufferReader.readLine()) != null) {
		        		buffer.append(str);
		        	}
		        	bufferReader.close();
		        	System.out.println(buffer.toString());
		        	//这里得到的是一个json数据类型的                                
		        	tvResult.setText(buffer.toString());
						                  //转换就省略了
				} catch (Exception e) {
					Log.i("mylog",e.toString());
					new RuntimeException(e);
				}
			}
	    });*/
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button2:
				new Thread() {  
		            public void run() {  
		            	//TextView tvResult = (TextView) findViewById(R.id.textView2);
		    			//tvResult.setText("dfgdfgdfg");
				        try {
				        	HttpClient client = new DefaultHttpClient();
				        	//tvResult.setText("1111");
				        	HttpGet httpGet = new HttpGet("http://130.215.173.207:8080/WebServices/market");
				        	//tvResult.setText("2222");
				        	HttpResponse response = client.execute(httpGet);
				        	//tvResult.setText("3333");
				        	InputStream inputStream = response.getEntity().getContent();
				        	//tvResult.setText("4444");
				          	StringBuffer buffer = new StringBuffer();
				           	BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
				          	String str = new String("");
				        	while ((str = bufferReader.readLine()) != null) {
				        		buffer.append(str);
				        	}
				        	bufferReader.close();
				        	System.out.println(buffer.toString());
				        	//这里得到的是一个json数据类型的    
				        	Message msg = new Message();
							msg.what = SUCCESS;// 获取成功时，发送消息为SUCCESS，即0；
							msg.obj = buffer;
							handler.sendMessage(msg);
				        	//tvResult.setText(buffer.toString());
								                  //转换就省略了*/
						} catch (Exception e) {
							Message msg = new Message();
							msg.what = FAILURE;// 获取失败时，发送消息FAILURE，即1；
							handler.sendMessage(msg);
							Log.i("mylog",e.toString());
							new RuntimeException(e);
						}
		            };  
		        }.start();  
		        break;
			case R.id.button1:
				new Thread() {  
		            public void run() {  
				        try {
				        	HttpClient client = new DefaultHttpClient();
				        	HttpGet httpGet = new HttpGet("http://130.215.173.207:8080/WebServices/events/refresh");
				        	HttpResponse response = client.execute(httpGet);		        	
				        	Message msg = new Message();
							msg.what = SUCCESS;// 获取成功时，发送消息为SUCCESS，即0；
							msg.obj = "refresh data";
							handler.sendMessage(msg);
				        }
				        catch (Exception e) {
							Message msg = new Message();
							msg.what = FAILURE;// 获取失败时，发送消息FAILURE，即1；
							handler.sendMessage(msg);
							Log.i("mylog",e.toString());
							new RuntimeException(e);
						}
		            };  
		        }.start();  
		        break;
		}
	}
	
	/*public void onClick(View view) {
        try {
        	TextView tvResult = (TextView) findViewById(R.id.textView2);
        	HttpClient client = new DefaultHttpClient();
        	HttpGet httpGet = new HttpGet("http://130.215.218.106:8080/WebServices/market");
        	HttpResponse response = client.execute(httpGet);
        	InputStream inputStream = response.getEntity().getContent();
          	StringBuffer buffer = new StringBuffer();
           	BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
          	String str = new String("");
        	while ((str = bufferReader.readLine()) != null) {
        		buffer.append(str);
        	}
        	bufferReader.close();
        	System.out.println(buffer.toString());
        	//这里得到的是一个json数据类型的                                
        	tvResult.setText(buffer.toString());
				                  //转换就省略了
			} catch (Throwable e) {
				new RuntimeException(e);
			}
	}*/
}
