package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

public interface StuSrv {

	void getInfo(Message rcvmsg, Socket socket) throws SQLException, IOException;

	void updateInfo(Message rcvmsg, Socket socket) throws SQLException, IOException;

}