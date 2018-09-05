package VC.server.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;
import VC.common.MessageType;

public class ServerThread implements Runnable {

	private Socket client;
	private String User;
	private boolean isClosed;

	public ServerThread(Socket s, String user) {
		this.setClient(s);
		this.setUser(user);
		this.setClosed(false);
	}

	public void run() {
		
		int i = 0;
		while (!isClosed()) {
			i++;
			Message rcvmsg = new Message();
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(client.getInputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rcvmsg = (Message) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("User: " + rcvmsg.getID() + " give the request " + i);
			System.out.println(rcvmsg.getType());
			
			if (rcvmsg.getType().equals(MessageType.CMD_JUDGE_LOGIN)) {

				boolean flag = false;
				LoginSrvImpl loginsrv = new LoginSrvImpl();
				
				try {
					flag = loginsrv.judgeLogin(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//start a new thread
					// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_QUY_BOOK_BOOKNAME)) {

				LibrarySrvImpl librarysrvimpl = new LibrarySrvImpl();
				try {
					librarysrvimpl.searchByBooknameSend(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_GOODS)) {

				ShopSrvImpl shopsrv = new ShopSrvImpl();
				try {
					shopsrv.getAllGoods(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_COURSE)) {

				CourseSrvImpl coursesrv = new CourseSrvImpl();
				try {
					coursesrv.getAllCourse(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_ADD_ALL_COURSE)) {

				CourseSrvImpl coursesrv = new CourseSrvImpl();
				try {
					coursesrv.addCourse(rcvmsg, client);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_DELETE_ALL_COURSE)) {

				CourseSrvImpl coursesrv = new CourseSrvImpl();
				try {
					coursesrv.delMyCourse(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_MYCOURSE)) {

				CourseSrvImpl coursesrv = new CourseSrvImpl();
				try {
					coursesrv.getMyCourse(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_BOOK)) {

				LibrarySrvImpl librarysrv = new LibrarySrvImpl();
				try {
					librarysrv.getAllBook(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_ADD_ALL_BOOK)) {

				LibrarySrvImpl librarysrv = new LibrarySrvImpl();
				try {
					librarysrv.borrowbook(rcvmsg, client);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_DELETE_ALL_BOOK)) {

				LibrarySrvImpl librarysrv = new LibrarySrvImpl();
				try {
					librarysrv.returnbook(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_MYBOOK)) {

				LibrarySrvImpl librarysrv = new LibrarySrvImpl();
				try {
					librarysrv.getMyBook(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_MY_USER)||rcvmsg.getType().equals(MessageType.CMD_QUY_USER)) {

				StuSrvImpl stusrv = new StuSrvImpl();
				try {
					stusrv.getInfo(rcvmsg, client);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_UPDATE_MY_USER)) {

				StuSrvImpl stusrv = new StuSrvImpl();
				try {
					stusrv.updateInfo(rcvmsg, client);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_LOGOUT)) {
				/*
				 * 		String logoutQQ = sender.getQqNo();
					// �ͻ����˳�
					// ���·�����
					sender.setStatus(UserStatus.OFFLINE);
					iud.updUser(sender);
					// ��֪ͨ�����������û�
					this.notifyOther(logoutQQ, MessageType.CMD_LOGOUT);				

					QqServerFrame.serverFrame.fireUserDataChange();
					//
					isClosed = true;
					ServerClientThreadMgr.remove(this.owner.getQqNo());
					break;
				 */
				isClosed = true;
				break;
			}
		}
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

}
