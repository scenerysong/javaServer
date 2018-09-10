package VC.server.vo;

import java.net.ServerSocket;

/**
 * 
 * @author song
 * 测试使用的单线程类
 */
public interface ServerSrv {

	/**
	 * 主运行功能
	 */
	void run();

	ServerSocket getServersocket();

	void setServersocket(ServerSocket serversocket);

	boolean isClosed();

	void setClosed(boolean closed);

}