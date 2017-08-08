package com.xiaohong.server.thread;

import java.util.*;

public class WebAPP {
	//为每一个Server_Context取一个别名
	//login-->Login_Context
	private static Map<String,Server_Context> serverlet;
	
	// url --> login
	// /log --> login
	// /login --> login  一个Server_Context可以针对多个映射，一个资源用多个路径来访问
		
	private static Map<String,String> mapping;
	
	//构造器
	public WebAPP(){
		
	}
//进行实例化
	static{
		serverlet = new HashMap<String,Server_Context>();
		mapping = new HashMap<String,String>();
		serverlet.put("login", new Login_Context());
		serverlet.put("register", new Register_Context());
		
		mapping.put("/login", "login");
		mapping.put("/log", "login");
		mapping.put("/register", "register");
		
	}
	public static Server_Context getServerContext(String url){
		if(url == null || (url = url.trim()).equals(""))
			return null;
		else
			return serverlet.get(mapping.get(url));
	}
}
