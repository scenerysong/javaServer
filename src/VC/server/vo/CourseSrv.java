package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

public interface CourseSrv {

	/* (non-Javadoc)
	 * @see VC.server.bz.CourseSrv#getAllCourse(VC.common.Message, java.net.Socket)
	 */
	void getAllCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/* (non-Javadoc)
	 * @see VC.server.bz.CourseSrv#addCourse(VC.common.Message, java.net.Socket)
	 */
	void addCourse(Message rcvmsg, Socket socket) throws SQLException, IOException;

	/* (non-Javadoc)
	 * @see VC.server.bz.CourseSrv#getMyCourse(VC.common.Message, java.net.Socket)
	 */
	void getMyCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/* (non-Javadoc)
	 * @see VC.server.bz.CourseSrv#delMyCourse(VC.common.Message, java.net.Socket)
	 */
	void delMyCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

}