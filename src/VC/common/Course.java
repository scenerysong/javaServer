package VC.common;

public class Course implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8251698032892536679L;
	private String courseName;
	private String score;
	private String courseTime;
	private String courseID;
	private String courseRoom;
	private String courseNum;
	private String courseTeacher;
	
    public Course() {
		
	}
	
	public Course(Course course) {
		this.setCourseName(course.getCourseName());
		this.setScore(course.getScore());
		this.setCourseTime(course.getCourseTime());
		this.setCourseID(course. getCourseID());
		this.setCourseRoom(course.getCourseRoom());
		this.setCourseNum(course.getCourseNum());
		this.setCourseTeacher(course.getCourseTeacher());
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseRoom() {
		return courseRoom;
	}
	public void setCourseRoom(String courseRoom) {
		this.courseRoom = courseRoom;
	}
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getCourseTeacher() {
		return courseTeacher;
	}
	public void setCourseTeacher(String courseTeacher) {
		this.courseTeacher = courseTeacher;
	}
	public String toString() {
		return "Course [courseName=" + courseName + ", courseID=" + courseID
				 + ", score=" + score+ ", courseTime=" + courseTime
				 + ", courseRoom=" + courseRoom+", courseNum=" + courseNum
				 + ", courseTeacher=" + courseTeacher;
	}
	
}
