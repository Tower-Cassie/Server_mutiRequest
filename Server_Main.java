package com.xiaohong.server.thread;

import java.io.*;
import java.net.*;


/**
 * 多线程：服务器响应多个客户端的请求
 * @author xiaohong
 *
 */
public class Server_Main {

	private ServerSocket server;
	private boolean isShutdown =false;
	public static void main(String[] args) throws IOException{
		Server_Main server = new Server_Main();
		server.start();
	}
	//启动方法
	public void start() throws IOException{
		server = new ServerSocket(8888);
		this.receive();
	}
	//指定端口的启动方法
	public void start(int port) throws IOException{
		server = new ServerSocket(port);
		this.receive();
	}
	//接收客户端
	private void receive() throws IOException{
		while(!isShutdown){
			Socket client = server.accept();
			new Thread(new Server_Thread(client)).start();
		}
	}
	//停止服务
	public void stop(){
		isShutdown = true;
		
	}
}
