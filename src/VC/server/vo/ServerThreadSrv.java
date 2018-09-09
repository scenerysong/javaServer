package VC.server.vo;

import java.net.Socket;

public interface ServerThreadSrv {

	void run();

	Socket getClient();

	void setClient(Socket client);

	String getUser();

	void setUser(String user);

	boolean isClosed();

	void setClosed(boolean isClosed);

}