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
}
