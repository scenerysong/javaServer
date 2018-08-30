package VC.server.bz;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.LoginMessage;
import VC.common.Message;
import VC.server.dao.LoginDAO;

public class LoginSrvImpl {

	public LoginSrvImpl() {
		
	}
	
	public void judgeLogin(Message rcvmsg, Socket socket) throws SQLException, ClassNotFoundException, IOException {
		
		LoginDAO logindao = new LoginDAO();
		LoginMessage loginmsg = new LoginMessage();
		loginmsg = (LoginMessage) rcvmsg;
		
		LoginMessage sendmsg = new LoginMessage();
		sendmsg = loginmsg;
		sendmsg.setLoginstat(false);
		
		
		if(loginmsg.getPasswd().equals(logindao.getPasswd(loginmsg.getID()))) {
			sendmsg.setLoginstat(true);
		}
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
		
	}
}
