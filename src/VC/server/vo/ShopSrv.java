package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

public interface ShopSrv {

	void getAllGoods(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	void getMyGoods(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	void addshoppingcart(Message rcvmsg, Socket socket) throws SQLException, IOException;

	void delMygood(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	void payforMygood(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

}