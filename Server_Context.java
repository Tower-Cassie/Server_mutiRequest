package com.xiaohong.server.thread;

/**
 * ���ݲ�ͬ�������в�ͬ�Ļ�Ӧ
 * ����Ϊһ������
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
