package com.xiaohong.server.thread;

public class Login_Context extends Server_Context{

	@Override
	protected void doGet(Server_Request request, Server_Response response)
			throws Exception {
		if(request.method.equalsIgnoreCase("get")){
			String name = request.getParameter("uname");
			String pwd = request.getParameter("pwd");
			if(name != null && pwd != null && login(name,pwd))
				response.createContentln("��½�ɹ���");
			else
				response.createContentln("��½ʧ�ܣ�");
			
		}
		
	}
	public boolean login(String name,String pwd){
		return name.equals("123") && pwd.equals("123");
		
	}

	@Override
	protected void doPost(Server_Request request, Server_Response response)
			throws Exception {
		if(request.method.equalsIgnoreCase("post")){
			response.createContentln("<html><head><title> ��ӭ����</title>");
			response.createContentln("</head><body>");
			response.createContentln("��ӭ").createContentln(request.getParameter("uname")).createContentln("����");
			response.createContentln("</body></html>");	
		}
		
	}

}
