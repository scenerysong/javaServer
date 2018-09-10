package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

/**
 * 
 * @author song
 * 服务器图书馆功能实现,解析接受的数据,并与客户端直连
 */
public interface LibrarySrv {

	/**
	 * 按书名查询书籍信息,并发送
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void searchByBooknameSend(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/**
	 * 得到所有书籍,并发送
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void getAllBook(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/**
	 * 得到所有用户已借的书,并发送
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void getMyBook(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/**
	 * 按照解析得到的用户,和书名来添加用户借的书,并发送是否成功
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 */
	void borrowbook(Message rcvmsg, Socket socket) throws SQLException, IOException;

	/**
	 * 按照解析得到的用户,和书名来删除用户借的书,并发送是否成功
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void returnbook(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

}