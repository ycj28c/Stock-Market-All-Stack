package com.tools;

import java.net.InetAddress;

public class networkTool {

	public String getLocalIP(){
		String ip = null;
		try{
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();// 获得本机IP
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}
}
