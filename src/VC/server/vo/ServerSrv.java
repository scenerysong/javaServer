package VC.server.vo;

import java.net.ServerSocket;

public interface ServerSrv {

	void run();

	ServerSocket getServersocket();

	void setServersocket(ServerSocket serversocket);

	boolean isClosed();

	void setClosed(boolean closed);

}