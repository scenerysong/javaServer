package VC.server.bz;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import VC.server.vo.LoginSrv;

public class LoginSrvImpl implements LoginSrv {

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
	
	/* (non-Javadoc)
	 * @see VC.server.bz.LoginSrv#judgeLogin(VC.common.Message, java.net.Socket)
	 */
	@Override
	public boolean judgeLogin(Message rcvmsg, Socket socket) throws SQLException, ClassNotFoundException, IOException {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean flag = false;
		//LoginDAO logindao = new LoginDAO();
		LoginMessage loginmsg = new LoginMessage();
		loginmsg = (LoginMessage) rcvmsg;
		
		LoginMessage sendmsg = new LoginMessage();
		sendmsg = loginmsg;
		sendmsg.setLoginstat(false);
		
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		BasicTextEncryptor encryptorText = new BasicTextEncryptor();
		if(encryptor.checkPassword(encryptorText.decrypt(loginmsg.getPasswd()), logindao.getPasswd(loginmsg.getID()))) {
			sendmsg.setLoginstat(true);
			flag = true;
			ServerStarter.adduser("登陆时间：  " + df.format(new Date()) + "  用户名： " +  rcvmsg.getID());
		}
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
		
		return flag;
		
	}
	
	/* (non-Javadoc)
	 * @see VC.server.bz.LoginSrv#addUser(VC.common.Message, java.net.Socket)
	 */
	@Override
	public void addUser(Message rcvmsg, Socket socket) throws SQLException, IOException {
		LoginMessage sendmsg = new LoginMessage();
		boolean res = false;
		LoginMessage rmsg = (LoginMessage) rcvmsg;
		
		String a = rmsg.getID();
		
		BasicTextEncryptor encryptorText = new BasicTextEncryptor();
		String ifsuperuser = "user";
		if(encryptorText.decrypt(rmsg.getAdmincode()).equals("2333"))
			ifsuperuser = "superuser";
		String b = encryptorText.decrypt(rmsg.getPasswd());
		System.out.println("this is srv step");
		System.out.println(rmsg.getID());
		System.out.println(rmsg.getPasswd());
		System.out.println(b);
		System.out.println(ifsuperuser);
		
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		String pwd = encryptor.encryptPassword(b);
		res = logindao.addUser(a,pwd, ifsuperuser);
		
		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	/* (non-Javadoc)
	 * @see VC.server.bz.LoginSrv#delUser(VC.common.Message, java.net.Socket)
	 */
	@Override
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
