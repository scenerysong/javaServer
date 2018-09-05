package VC.server.bz;

import VC.server.vo.StuSrv;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import VC.common.Message;
import VC.common.User;
import VC.common.UserMessage;
import VC.server.dao.StuDAO;

public class StuSrvImpl implements StuSrv{

	public StuDAO studao;
	
	public StuSrvImpl() {
		
		try {
			studao = new StuDAO();
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

		System.out.println(Usermsg.getUsername());
		inf = studao.getUserInf(Usermsg.getUsername());
		System.out.println("in the stusrvimpl part " + inf.getPersonname());
		sendmsg.setUser(inf);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	public void updateInfo(Message rcvmsg, Socket socket) throws SQLException, IOException {
		UserMessage Usermsg = new UserMessage();
		Usermsg = (UserMessage)rcvmsg;
		UserMessage sendmsg = new UserMessage();
		String username = Usermsg.getUsername();
		
		sendmsg.setRes(studao.modifyinf(username, Usermsg.getUser()));
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
}
