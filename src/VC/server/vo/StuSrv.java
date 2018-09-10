package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

/**
 * 
 * @author song
 *服务器学生学籍功能实现,解析接受的数据,并与客户端直连
 */
public interface StuSrv {

	/**
	 * 根据解析到的用户,判断权限,若为本人请求本人数据或管理员请求数据,则发送用户信息
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 */
	void getInfo(Message rcvmsg, Socket socket) throws SQLException, IOException;

	/**
	 * 根据解析到的用户及其信息,更新此用户信息
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 */
	void updateInfo(Message rcvmsg, Socket socket) throws SQLException, IOException;

}