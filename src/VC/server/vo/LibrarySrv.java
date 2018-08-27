package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

public interface LibrarySrv {

	public void searchByBooknameSend(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;
	
}
