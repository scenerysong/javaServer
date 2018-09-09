package VC.server.bz;

import VC.server.vo.StuSrv;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.common.BookMessage;
import VC.common.Message;
import VC.common.User;
import VC.common.UserMessage;
import VC.server.dao.LibraryDAO;
import VC.server.dao.LoginDAO;
import VC.server.dao.StuDAO;
import VC.server.vo.LibrarySrv;

public class StuSrvImpl implements StuSrv{

	public StuDAO studao;
	private LoginDAO logindao;
	
	public StuSrvImpl() {
		
		try {
			studao = new StuDAO();
			logindao = new LoginDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getInfo(Message rcvmsg, Socket socket) throws SQLException, IOException {
		User inf = new User();
		UserMessage Usermsg = new UserMessage();
		Usermsg = (UserMessage) rcvmsg;
		UserMessage sendmsg = new UserMessage();

		System.out.println(logindao.JudgeUserType(Usermsg.getID()));
		System.out.println(Usermsg.getID().equals(Usermsg.getUsername()));
		if(logindao.JudgeUserType(Usermsg.getID())||Usermsg.getID().equals(Usermsg.getUsername())) {
			inf = studao.getUserInf(Usermsg.getUsername());
			sendmsg.setUser(inf);
		}
		else sendmsg.setRes(false);
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	public void updateInfo(Message rcvmsg, Socket socket) throws SQLException, IOException {
			
		UserMessage Usermsg = new UserMessage();
		Usermsg = (UserMessage)rcvmsg;
		UserMessage sendmsg = new UserMessage();
		String username = Usermsg.getID();
		
		sendmsg.setRes(studao.modifyinf(username, Usermsg.getUser()));
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
}
