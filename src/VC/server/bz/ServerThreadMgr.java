package VC.server.bz;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class ServerThreadMgr {

	private  static  Map<String, ServerThread>  clientThreadPool = new LinkedHashMap<String, ServerThread>();

	public synchronized static void add(String userNo, ServerThread clientThreadSrv) {
		clientThreadPool.put(userNo, clientThreadSrv);		
	}

	public synchronized static ServerThread remove(String userNo) {
		return clientThreadPool.remove(userNo);		
	}
	public synchronized static ServerThread get(String userNo) {
		ServerThread cts = clientThreadPool.get(userNo);		
		return cts;		
	}
	public synchronized static Map<String, ServerThread> getPool(){
		return clientThreadPool;
	}

	//返回当前在线人的情况
	public synchronized static String getAllOnLineUserid()
	{
		//使用迭代器完成
		Iterator<String> it = clientThreadPool.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}

	public synchronized static void clear() {
		clientThreadPool.clear();		
	}

}
