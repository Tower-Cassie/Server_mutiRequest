package com.xiaohong.server.thread;

public class Register_Context extends Server_Context{

	@Override
	protected void doGet(Server_Request request, Server_Response response)
			throws Exception {
		if(request.method.equalsIgnoreCase("get")){
			response.createContentln("<html><head><title> �û�ע��</title>");
			response.createContentln("</head><body>");
			response.createContentln("register���õķ�����get");
			response.createContentln("</body></html>");	
		}
	}

	@Override
	protected void doPost(Server_Request request, Server_Response response)
			throws Exception {
		if(request.method.equalsIgnoreCase("post")){
			response.createContentln("<html><head><title> �û�ע��</title>");
			response.createContentln("</head><body>");
			response.createContentln("����û�����" + request.getParameter("uname"));
			response.createContentln("</body></html>");	
		}
	}

}
