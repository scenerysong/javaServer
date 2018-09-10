package VC.server.vo;

import java.net.Socket;

/**
 * 
 * @author song
 * 子线程类,用于判断消息需求的功能
 */
public interface ServerThreadSrv {

	/**
	 * 主运行功能
	 */
	void run();

	Socket getClient();

	void setClient(Socket client);

	String getUser();

	void setUser(String user);

	boolean isClosed();

	void setClosed(boolean isClosed);

}