package VC.common;

public class LoginMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7420984305071859572L;
	private boolean loginstat;
	private String passwd;

	public boolean isLoginstat() {
		return loginstat;
	}

	public void setLoginstat(boolean loginstat) {
		this.loginstat = loginstat;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
