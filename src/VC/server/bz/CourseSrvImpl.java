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

	public CourseSrvImpl() {
		
	}
	
	public void getAllCourse(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
		
		CourseDAO coursedao = new CourseDAO();
		List<Course> Courselist = new ArrayList<Course>();
		CourseMessage sendmsg = new CourseMessage();
		
		//调用dao里的方法
		Courselist = coursedao.getAllCourse();
		
		//test
		System.out.println(Courselist.get(1).toString());
		sendmsg.setCourselist(Courselist);
		
		//发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
}
