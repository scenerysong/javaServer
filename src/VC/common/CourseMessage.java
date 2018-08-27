package VC.common;

public class CourseMessage  extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4338384697590639870L;
	private String courseName;
	private String score;
	private String courseTime;
	private String courseID;
	private String courseRoom;
	private String courseNum;
	private String courseTeacher;
	
    public CourseMessage(){
		
	}
	
	public String courseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String score() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String courseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public String courseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String courseRoom() {
		return courseRoom;
	}
	public void setCourseRoom(String courseRoom) {
		this.courseRoom = courseRoom;
	}
	public String courseTeacher() {
		return courseTeacher;
	}
	public void setCourseTeacher(String courseTeacher) {
		this.courseTeacher = courseTeacher;
	}
	public String courseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

}
