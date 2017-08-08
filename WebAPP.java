package com.xiaohong.server.thread;

import java.util.*;

public class WebAPP {
	//Ϊÿһ��Server_Contextȡһ������
	//login-->Login_Context
	private static Map<String,Server_Context> serverlet;
	
	// url --> login
	// /log --> login
	// /login --> login  һ��Server_Context������Զ��ӳ�䣬һ����Դ�ö��·��������
		
	private static Map<String,String> mapping;
	
	//������
	public WebAPP(){
		
	}
//����ʵ����
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
