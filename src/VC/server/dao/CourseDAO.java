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
			
			Course.add(course);
		}
		
		return Course;
	}
	
	// add the courses for the users
	public boolean AddCourse(String Users, String coursename) throws SQLException {
		sql = "selct * from course where coursename = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, coursename);
		rs = ps.executeQuery();
		
		/* ；课程名称默认不重复*/
		Course crs = new Course();
		if(rs.next()) {
			crs.setCourseID(rs.getString("ID"));
			crs.setCourseName(rs.getString("coursename"));
			crs.setCourseTeacher(rs.getString("teacher"));
			sql = "insert into CourseUser values (?, ?, ?, ?, ?)";
			ps = ct.prepareStatement(sql);
			ps.setString(1, crs.getCourseID());
			ps.setString(2, Users);
			ps.setString(3, crs.getCourseName());
			ps.setString(4, crs.getCourseTeacher());
			ps.setString(5, crs.getCredit());
			if(ps.executeUpdate() > 0) return true;
			else return false;
		}
		else return false;
	}
	
	
	// return all the courses the user has chose.
	public List<Course> GetAllMyCourse(String Users) throws SQLException {
		sql = "select * from CourseUser where User = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		rs = ps.executeQuery();
		
		Course crs = new Course();
		List<Course> Crs = new ArrayList<Course>();
		
		while(rs.next()) {
			crs.setCourseName(rs.getString("ID"));
			crs.setCourseID(rs.getString("CourseName"));
			crs.setCourseTeacher(rs.getString("Teacher"));
			crs.setCredit(rs.getString("Credit"));
			
			Crs.add(crs);
		}
		return Crs;
	}
	
	
	//delete the record of ChoosedCourse
	public boolean DelCourse(String Users, String coursename) throws SQLException {
		sql = "delete from CourseUser where User = ? and CourseName = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		ps.setString(2, coursename);
		if(ps.executeUpdate() > 0) return true;
		else return false;
	}
}
