package com.xiaohong.server.thread;

import java.io.*;
import java.net.*;


/**
 * ���̣߳���������Ӧ����ͻ��˵�����
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
	//��������
	public void start() throws IOException{
		server = new ServerSocket(8888);
		this.receive();
	}
	//ָ���˿ڵ���������
	public void start(int port) throws IOException{
		server = new ServerSocket(port);
		this.receive();
	}
	//���տͻ���
	private void receive() throws IOException{
		while(!isShutdown){
			Socket client = server.accept();
			new Thread(new Server_Thread(client)).start();
		}
	}
	//ֹͣ����
	public void stop(){
		isShutdown = true;
		
	}
}
