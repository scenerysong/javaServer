package VC.server.bz;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.CourseMessage;
import VC.common.LoginMessage;
import VC.common.Message;
import VC.common.User;
import VC.common.UserMessage;
import VC.server.dao.LoginDAO;

public class LoginSrvImpl {

	private LoginDAO logindao;
	
	public LoginSrvImpl() {
		
	}
	
	public boolean judgeLogin(Message rcvmsg, Socket socket) throws SQLException, ClassNotFoundException, IOException {
		
		boolean flag = false;
		//LoginDAO logindao = new LoginDAO();
		LoginMessage loginmsg = new LoginMessage();
		loginmsg = (LoginMessage) rcvmsg;
		
		LoginMessage sendmsg = new LoginMessage();
		sendmsg = loginmsg;
		sendmsg.setLoginstat(false);
		
		
		if(loginmsg.getPasswd().equals(logindao.getPasswd(loginmsg.getID()))) {
			sendmsg.setLoginstat(true);
			flag = true;
		}
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
		
		return flag;
		
	}
	
	public void addUser(Message rcvmsg, Socket socket) throws SQLException, IOException {
		LoginMessage sendmsg = new LoginMessage();
		boolean res = false;
		LoginMessage rmsg = (LoginMessage) rcvmsg;
		String username = rmsg.getID();
		res = logindao.addUser(rmsg.getID(), rmsg.getPasswd(), rmsg.getAdmincode());
		
		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	public void delUser(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
		
		LoginMessage sendmsg = new LoginMessage();
		boolean res = false;
		LoginMessage rmsg = (LoginMessage) rcvmsg;
		String username = rmsg.getID();
		
		res = logindao.delUser(username);

		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
}
