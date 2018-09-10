package VC.common;

/**
 * 用于学生学籍模块的信息类型
 */
public class UserMessage extends Message{

	
	private static final long serialVersionUID = 3811042105596867607L;
	private String username;
	private User user;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
