package VC.server.vo;

import java.net.ServerSocket;

/**
 * 
 * @author song
 * 多线程的总线程端,子线程从这里判断分出. 在这里判断接受服务器
 */
public interface MultiServer {

	/**
	 * 主运行功能
	 */
	void run();

	ServerSocket getServersocket();

	void setServersocket(ServerSocket serversocket);

	boolean isClosed();

	void setClosed(boolean closed);

}