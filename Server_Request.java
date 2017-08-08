package com.xiaohong.server.thread;

import java.io.*;
import java.util.*;


public class Server_Request {
	//请求方式
		public String method;
		//请求资源
		private String url;
		//请求参数
		private static Map<String,List<String>> userInfo;
		//内部属性
		public static final String CRLF = "\r\n";
		private InputStream is;
		private String requestInfo;
		//构造器
		public Server_Request(){
			method = "";//避免指向空指针
			url = "";
			userInfo = new HashMap<String,List<String>>();
			requestInfo = "";
		}
		public Server_Request(InputStream is) throws IOException{
			this();
			this.is = is;
			byte[] data = new byte[20480];
			int len = is.read(data);//len是内部数据，所以放在该方法中
			requestInfo = new String(data,0,len);
			
			//分析头信息
			AnalyseRequestInfo();
		}
		/**
		 * 分析请求信息
		 */
		public void AnalyseRequestInfo(){
			if(requestInfo == null || (requestInfo = requestInfo.trim()).equals(""))
				return;
			/**
			 * =========================================
			 * 从信息首行分解出：请求方式       请求路径    请求参数（get可能存在）
			 *     如：get/index.html?name=123&pwd=123 HTTP/1.1
			 *     
			 *     
			 * 如果为post方式，请求参数可能在最后的正文中 
			 * ============================================
			 */
			String nameInfo = "";//接收请求参数
			//1.获取请求方式
			String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
			int index = firstLine.indexOf("/");//字符/的位置
			this.method = firstLine.substring(0, index).trim();//去除空格
			String urlStr = firstLine.substring(index, firstLine.indexOf("HTTP/"));//获取路径
			
			
			if(this.method.equalsIgnoreCase("post")){
				this.url = urlStr;
				nameInfo = requestInfo.substring(requestInfo.lastIndexOf(CRLF));
				
			}else if(this.method.equalsIgnoreCase("get")){
				if(urlStr.contains("?")){//是否存在参数
					String[] urlArray = urlStr.split("\\?");//以字符?分隔开
					this.url = urlArray[0];
					nameInfo = urlArray[1];
				}else{
					this.url = urlStr;
				}	
			}
			
			//2,将请求参数封装到Map中
			PacketMap(nameInfo);
			
		}
		public static void PacketMap(String nameInfo){
			//分割   将字符串转换成数组
			StringTokenizer token = new StringTokenizer(nameInfo,"&");//相当于spilt的功能
			while(token.hasMoreTokens()){
				String keyValue = token.nextToken();
				String[] keyValues = keyValue.split("=");
				if(keyValues.length == 1){
					keyValues = Arrays.copyOf(keyValues, 2);
					keyValues[1] = null;
				}
				String key = keyValues[0].trim();
				String value = keyValues[1] == null ? null:(decode(keyValues[1].trim(),"gbk"));
				
				//分割成Map 分拣
				if( !userInfo.containsKey(key))
					userInfo.put(key, new ArrayList<String>());
				
				List<String> values =  userInfo.get(key);
				values.add(value);
			}
		}
		/**
		 * 根据页面的name获取对应的值
		 * @param args
		 * @throws IOException
		 */
		//获取一个值
		public String getParameter(String name){
			String[] values = getParameterValues(name);
			if(values == null) return null;
			else return values[0];
		}
		//获取多个值
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
