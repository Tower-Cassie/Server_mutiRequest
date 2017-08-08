package com.xiaohong.server.thread;

import java.io.*;
import java.util.*;


public class Server_Request {
	//����ʽ
		public String method;
		//������Դ
		private String url;
		//�������
		private static Map<String,List<String>> userInfo;
		//�ڲ�����
		public static final String CRLF = "\r\n";
		private InputStream is;
		private String requestInfo;
		//������
		public Server_Request(){
			method = "";//����ָ���ָ��
			url = "";
			userInfo = new HashMap<String,List<String>>();
			requestInfo = "";
		}
		public Server_Request(InputStream is) throws IOException{
			this();
			this.is = is;
			byte[] data = new byte[20480];
			int len = is.read(data);//len���ڲ����ݣ����Է��ڸ÷�����
			requestInfo = new String(data,0,len);
			
			//����ͷ��Ϣ
			AnalyseRequestInfo();
		}
		/**
		 * ����������Ϣ
		 */
		public void AnalyseRequestInfo(){
			if(requestInfo == null || (requestInfo = requestInfo.trim()).equals(""))
				return;
			/**
			 * =========================================
			 * ����Ϣ���зֽ��������ʽ       ����·��    ���������get���ܴ��ڣ�
			 *     �磺get/index.html?name=123&pwd=123 HTTP/1.1
			 *     
			 *     
			 * ���Ϊpost��ʽ������������������������� 
			 * ============================================
			 */
			String nameInfo = "";//�����������
			//1.��ȡ����ʽ
			String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
			int index = firstLine.indexOf("/");//�ַ�/��λ��
			this.method = firstLine.substring(0, index).trim();//ȥ���ո�
			String urlStr = firstLine.substring(index, firstLine.indexOf("HTTP/"));//��ȡ·��
			
			
			if(this.method.equalsIgnoreCase("post")){
				this.url = urlStr;
				nameInfo = requestInfo.substring(requestInfo.lastIndexOf(CRLF));
				
			}else if(this.method.equalsIgnoreCase("get")){
				if(urlStr.contains("?")){//�Ƿ���ڲ���
					String[] urlArray = urlStr.split("\\?");//���ַ�?�ָ���
					this.url = urlArray[0];
					nameInfo = urlArray[1];
				}else{
					this.url = urlStr;
				}	
			}
			
			//2,�����������װ��Map��
			PacketMap(nameInfo);
			
		}
		public static void PacketMap(String nameInfo){
			//�ָ�   ���ַ���ת��������
			StringTokenizer token = new StringTokenizer(nameInfo,"&");//�൱��spilt�Ĺ���
			while(token.hasMoreTokens()){
				String keyValue = token.nextToken();
				String[] keyValues = keyValue.split("=");
				if(keyValues.length == 1){
					keyValues = Arrays.copyOf(keyValues, 2);
					keyValues[1] = null;
				}
				String key = keyValues[0].trim();
				String value = keyValues[1] == null ? null:(decode(keyValues[1].trim(),"gbk"));
				
				//�ָ��Map �ּ�
				if( !userInfo.containsKey(key))
					userInfo.put(key, new ArrayList<String>());
				
				List<String> values =  userInfo.get(key);
				values.add(value);
			}
		}
		/**
		 * ����ҳ���name��ȡ��Ӧ��ֵ
		 * @param args
		 * @throws IOException
		 */
		//��ȡһ��ֵ
		public String getParameter(String name){
			String[] values = getParameterValues(name);
			if(values == null) return null;
			else return values[0];
		}
		//��ȡ���ֵ
		public String[] getParameterValues(String name){
			List<String> values = null;
			if((values = userInfo.get(name)) == null)
				return null;
			else
				return  values.toArray(new String[0]);
		}
		
		
		private static String decode(String value,String code){
			try {
				return java.net.URLDecoder.decode(value,code);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}
		public String getURL(){
			return this.url;
		}
	}
