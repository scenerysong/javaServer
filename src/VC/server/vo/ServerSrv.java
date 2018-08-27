package VC.server.vo;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

import VC.common.SocketConstant;

public interface ServerSrv {

	ServerSocket serversocket = null;
	static final int SERVER_PORT = SocketConstant.SERVER_PORT;
	boolean closed = false;

	public void run() throws IOException, ClassNotFoundException, SQLException;
	public ServerSocket getServersocket();
	public void setServersocket(ServerSocket serversocket);
	public boolean isClosed();
	public void setClosed(boolean closed);
	
}
