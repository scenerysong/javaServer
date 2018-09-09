package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

public interface LoginSrv {

	boolean judgeLogin(Message rcvmsg, Socket socket) throws SQLException, ClassNotFoundException, IOException;

	void addUser(Message rcvmsg, Socket socket) throws SQLException, IOException;

	void delUser(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

}