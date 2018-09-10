package VC.common;

/**
 * 信息的基础类 <br>
 * 其余信息继承此类产生
 * 
 * @author song
 */
public class Message implements java.io.Serializable{
	
	private static final long serialVersionUID = 2467300089917028886L;
	private String ID;
	private String type;
	private boolean res;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public boolean isRes() {
		return res;
	}
	public void setRes(boolean res) {
		this.res = res;
	}

}
