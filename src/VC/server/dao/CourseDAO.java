package VC.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Course;
import VC.server.db.DBstart;

public class CourseDAO extends DBstart{

	public CourseDAO() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Course> getAllCourse() throws SQLException{
		
		sql = "select * from course";
		ps = ct.prepareStatement(sql);
		rs = ps.executeQuery();
		
		Course course = new Course();
		List<Course> Course = new ArrayList<Course>();
		
		while(rs.next()) {
			
			course= new Course();
			course.setCourseName(rs.getString("coursename"));
			course.setCourseTeacher(rs.getString("teacher"));
			course.setCredit(rs.getString("Credit"));
			course.setCourseID(rs.getString("courseid"));
			
			Course.add(course);
		}
		
		return Course;
	}
	
	public boolean AddCourse(String Users, String coursename) throws SQLException {
		sql = "select * from course where coursename = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, coursename);
		rs = ps.executeQuery();
		
		/* ；课程名称默认不重复*/
		Course crs = new Course();
		if(rs.next()) {
			System.out.println(rs.getString("coursename"));
			crs.setCourseID(rs.getString("courseid"));
			crs.setCourseName(rs.getString("coursename"));
			crs.setCourseTeacher(rs.getString("teacher"));
			crs.setCredit(rs.getString("Credit"));
			sql = "insert into CourseUser (User, CourseName, Teacher, Credit, courseid) values (?, ?, ?, ?, ?) ";
			ps = ct.prepareStatement(sql);
			//ps.setString(1, "6");
			ps.setString(1, Users);
			ps.setString(2, crs.getCourseName());
			ps.setString(3, crs.getCourseTeacher());
			ps.setString(4, crs.getCredit());
			ps.setString(5, crs.getCourseID());
			//ps.executeUpdate();
			ps.execute();
		}
		return true;
	}
	
	public List<Course> GetAllMyCourse(String Users) throws SQLException {
		sql = "select * from CourseUser where User = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		rs = ps.executeQuery();
		
		Course crs;
		List<Course> Crs = new ArrayList<Course>();
		
		while(rs.next()) {
			crs = new Course();
			crs.setCourseName(rs.getString("CourseName"));
			crs.setCourseID(rs.getString("courseid"));
			System.out.println(rs.getString("courseid"));
			crs.setCourseTeacher(rs.getString("Teacher"));
			crs.setCredit(rs.getString("Credit"));
			
			Crs.add(crs);
		}
		return Crs;
	}
	
	
	//delete the record of ChoosedCourse
	public boolean DelCourse(String Users, String coursename) throws SQLException {
		System.out.println("kai shi tui ke step3");
		sql = "delete from CourseUser where User = ? and CourseName = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		ps.setString(2, coursename);
		if(ps.executeUpdate() > 0) return true;
		else return false;
	}
}
