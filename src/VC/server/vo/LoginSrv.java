package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

/**
 * 
 * @author song
 *服务器登录功能实现,解析接受的数据,并与客户端直连
 */
public interface LoginSrv {

	/**
	 * 根据解析得到的用户名,密码判断是否登录成功,并返回是否登录成功的布尔值
	 * @param rcvmsg
	 * @param socket
	 * @return boolean值,返回用户是否登录成功
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	boolean judgeLogin(Message rcvmsg, Socket socket) throws SQLException, ClassNotFoundException, IOException;

	/**
	 * 根据解析得到的用户名,密码,授权码判断是否能够添加,并判断添加的用户权限
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 */
	void addUser(Message rcvmsg, Socket socket) throws SQLException, IOException;

	/**
	 * 根据解析得到的用户名,删除用户
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void delUser(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

}