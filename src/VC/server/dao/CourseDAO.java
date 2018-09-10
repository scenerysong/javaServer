package VC.server.dao;

/**
 * class {@code CourseDAO} is the subclass of class {@link VC.server.db.DBstart} for database.
 * <p>it is used to get the information from database according to the requirements and do some simple data processing if needed.
 * including methods for courses.
 * 
 * @see
 * 
 * @author Guangwei Xiong
 * @author Linsong Wang 
 * 
 * @version 1.0
*/

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

	/**
	 * 
	 * get all of the course in the database
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * CourseDAO coursedao = new CourseDAO();
	 * 
	 * List<Course> cs = new ArrayList<Course>();
	 * cs = coursedao.getAllCourse();
	 * }
	 * 
	 * @return all courses in database
	 * @throws SQLException
	 */
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
	
	/**
	 * 
	 * add a course for a user
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * String coursename = "history";
	 * CourseDAO coursedao = new CourseDAO();
	 * 
	 * flag = coursedao.AddCourse(User, coursename);
	 * }
	 * 
	 * @param Users
	 * @param coursename
	 * @return the result of operation
	 * @throws SQLException
	 */
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
	
	/**
	 * 
	 * get all of the course the user has chosen 
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * CourseDAO coursedao = new CourseDAO();
	 * 
	 * List<Course> cs = new ArrayList<Course>();
	 * cs = coursedao.getAllMyCourse(User);
	 * }
	 * 
	 * @param Users
	 * @return all of the courses the user has chosen
	 * @throws SQLException
	 */
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
	
	/**
	 * 
	 * delete the record of ChoosedCourse
	 * 
	 * <p><pre>{@code
	 * show how to use this method
	 * 
	 * CourseDAO coursedao = new CourseDAO();
	 * String User = "wls";
	 * String coursesname = "Historty";
	 * 
	 * flag = coursedao.DelCourse(User, coursesname);
	 * }
	 * 
	 * @param Users
	 * @param coursename
	 * @return the result of operation
	 * @throws SQLException
	 */
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
