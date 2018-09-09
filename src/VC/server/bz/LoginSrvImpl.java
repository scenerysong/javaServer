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
		try {
			logindao = new LoginDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// check the password of a user and return the result of the login
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
	
	// register for a user
	public void addUser(Message rcvmsg, Socket socket) throws SQLException, IOException {
		LoginMessage sendmsg = new LoginMessage();
		boolean res = false;
		LoginMessage rmsg = (LoginMessage) rcvmsg;
		
		String a = rmsg.getID();
		String b = rmsg.getPasswd();
		
		String ifsuperuser = "user";
		
		//check the code for superuser
		if(rmsg.getAdmincode().equals("23333"))
			ifsuperuser = "superuser";
		System.out.println("this is srv step");
		System.out.println(rmsg.getID());
		System.out.println(rmsg.getPasswd());
		System.out.println(ifsuperuser);
		res = logindao.addUser(a,b, ifsuperuser);
		
		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	//delete a course the user has chosen and return the result of the operation
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
