package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

/**
 * 
 * @author song
 *服务器选课功能实现,解析接受的数据,并与客户端直连
 */
public interface CourseSrv {

	/**
	 * 得到所有的课程,并发送
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void getAllCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/**
	 * 添加课程,并发送是否成功
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 */
	void addCourse(Message rcvmsg, Socket socket) throws SQLException, IOException;

	/**
	 * 得到用户的所有选课,并发送
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void getMyCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/**
	 * 删除用户的选课,并发送是否成功
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void delMyCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

}