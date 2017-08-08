package com.xiaohong.server.thread;

/**
 * 根据不同的请求有不同的回应
 * 抽象为一个父类
 * @author xiaohong
 *
 */
public abstract class Server_Context {
	public  void service(Server_Request request,Server_Response response)throws Exception{
		this.doGet(request,response);
		this.doPost(request, response);
	}
	protected abstract void doGet(Server_Request request,Server_Response response)throws Exception;
	protected abstract void doPost(Server_Request request,Server_Response response)throws Exception;
}
