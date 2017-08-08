package com.xiaohong.server.thread;

public class Register_Context extends Server_Context{

	@Override
	protected void doGet(Server_Request request, Server_Response response)
			throws Exception {
		if(request.method.equalsIgnoreCase("get")){
			response.createContentln("<html><head><title> 用户注册</title>");
			response.createContentln("</head><body>");
			response.createContentln("register采用的方法是get");
			response.createContentln("</body></html>");	
		}
	}

	@Override
	protected void doPost(Server_Request request, Server_Response response)
			throws Exception {
		if(request.method.equalsIgnoreCase("post")){
			response.createContentln("<html><head><title> 用户注册</title>");
			response.createContentln("</head><body>");
			response.createContentln("你的用户名：" + request.getParameter("uname"));
			response.createContentln("</body></html>");	
		}
	}

}
