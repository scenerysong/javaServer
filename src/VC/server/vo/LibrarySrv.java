package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

public interface LibrarySrv {

	// 注意此处传入的参数
	void searchByBooknameSend(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	void getAllBook(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	void getMyBook(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	void borrowbook(Message rcvmsg, Socket socket) throws SQLException, IOException;

	void returnbook(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

}