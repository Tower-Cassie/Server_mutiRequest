package com.xiaohong.server.thread;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
	public static void CloseAll(Closeable... io) throws IOException{
		for(Closeable index:io)
			if(index != null)
				index.close();
			else continue;
		
	}

}
