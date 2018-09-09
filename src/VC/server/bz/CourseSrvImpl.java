package VC.server.bz;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Course;
import VC.common.CourseMessage;
import VC.common.Message;
import VC.server.dao.CourseDAO;

public class CourseSrvImpl {

	public CourseDAO coursedao;

	public CourseSrvImpl() {
		try {
			coursedao = new CourseDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//get all courses in the database and send the message to the client
	public void getAllCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {

		List<Course> Courselist = new ArrayList<Course>();
		CourseMessage sendmsg = new CourseMessage();

		// 调用dao里的方法
		Courselist = coursedao.getAllCourse();

		// test
		sendmsg.setCourselist(Courselist);

		// 发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}

	// add a course for a user and return the result of the operation
	public void addCourse(Message rcvmsg, Socket socket) throws SQLException, IOException {

		CourseMessage sendmsg = new CourseMessage();
		String coursename = null;
		String username = null;
		boolean res = false;
		CourseMessage rmsg = (CourseMessage) rcvmsg;
		coursename = rmsg.courseName();
		username = rmsg.getID();
		
		res = coursedao.AddCourse(username, coursename);

		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}

	//get all of the courses a user has chosen and send the message to the client
	public void getMyCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {

		List<Course> Courselist = new ArrayList<Course>();
		CourseMessage sendmsg = new CourseMessage();
		String username = rcvmsg.getID();

		// 调用dao里的方法
		Courselist = coursedao.GetAllMyCourse(username);

		// test
		sendmsg.setCourselist(Courselist);

		// 发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	//delete a course the user has chosen and return the result of the operation
	public void delMyCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
		
		CourseMessage sendmsg = new CourseMessage();
		String coursename = null;
		String username = null;
		boolean res = false;
		CourseMessage rmsg = (CourseMessage) rcvmsg;
		coursename = rmsg.courseName();
		username = rmsg.getID();
		
		System.out.println("kai shi tui ke step2");
		res = coursedao.DelCourse(username, coursename);

		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
}
