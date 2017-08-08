package com.xiaohong.server.thread;

import java.io.*;
import java.net.*;

public class Server_Thread implements Runnable{
	private Server_Request req;
	private Server_Response rep;
	private Socket client;
	private int code;
	private boolean  isRunning = true;
	
	public Server_Thread(Socket client){
		this.client =client;
		try {
			req = new Server_Request(client.getInputStream());
			rep = new Server_Response(client);
			this.code = 200;
		} catch (IOException e) {
			isRunning = false;
			code = 500;
			return;
		}
		//req.parseRequestInfo();
		
	}
	@Override
	public void run() {
			Server_Context serv = WebAPP.getServerContext(req.getURL());
			if(serv == null)
				this.code = 500;
			else{
				try {
					try {
						serv.service(req,rep);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					rep.pushToClient(code);//推送到客户端
				} catch (IOException e) {
					isRunning = false;
					code = 500;
					return;
				}
			}
		
		
	}
}
