package VC.common;

public class Message implements java.io.Serializable{
	


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2467300089917028886L;
	private String ID;
	private String type;
	//private String general_type;
	private String sender;
	private String receiver;
	private boolean res;
	
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
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
