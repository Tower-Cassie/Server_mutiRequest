package com.xiaohong.server.thread;

public class Login_Context extends Server_Context{

	@Override
	protected void doGet(Server_Request request, Server_Response response)
			throws Exception {
		if(request.method.equalsIgnoreCase("get")){
			String name = request.getParameter("uname");
			String pwd = request.getParameter("pwd");
			if(name != null && pwd != null && login(name,pwd))
				response.createContentln("µÇÂ½³É¹¦£¡");
			else
				response.createContentln("µÇÂ½Ê§°Ü£¡");
			
		}
		
	}
	public boolean login(String name,String pwd){
		return name.equals("123") && pwd.equals("123");
		
	}

	@Override
	protected void doPost(Server_Request request, Server_Response response)
			throws Exception {
		if(request.method.equalsIgnoreCase("post")){
			response.createContentln("<html><head><title> »¶Ó­»ØÀ´</title>");
			response.createContentln("</head><body>");
			response.createContentln("»¶Ó­").createContentln(request.getParameter("uname")).createContentln("»ØÀ´");
			response.createContentln("</body></html>");	
		}
		
	}

}
