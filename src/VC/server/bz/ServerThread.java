package VC.server.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;
import VC.common.MessageType;
import VC.server.vo.CourseSrv;
import VC.server.vo.LibrarySrv;
import VC.server.vo.LoginSrv;
import VC.server.vo.ServerThreadSrv;
import VC.server.vo.ShopSrv;
import VC.server.vo.StuSrv;

public class ServerThread implements Runnable, ServerThreadSrv {

	private Socket client;
	private String User;
	private boolean isClosed;

	public ServerThread(Socket s, String user) {
		this.setClient(s);
		this.setUser(user);
		this.setClosed(false);
	}

	/* (non-Javadoc)
	 * @see VC.server.bz.ServerThreadSrv#run()
	 */
	@Override
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
				LoginSrv loginsrv = new LoginSrvImpl();
				
				try {
					flag = loginsrv.judgeLogin(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//start a new thread
					// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_REGIS_LOGIN)) {
				LoginSrv loginsrv = new LoginSrvImpl();
				
				try {
					loginsrv.addUser(rcvmsg, client);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//start a new thread
					// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_DEL_LOGIN)) {
				LoginSrv loginsrv = new LoginSrvImpl();
				try {
					loginsrv.delUser(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//start a new thread
					// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_QUY_BOOK_BOOKNAME)) {

				LibrarySrv librarysrvimpl = new LibrarySrvImpl();
				try {
					librarysrvimpl.searchByBooknameSend(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_GOODS)) {

				ShopSrv shopsrv = new ShopSrvImpl();
				try {
					shopsrv.getAllGoods(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_MYGOODS)) {

				ShopSrv shopsrv = new ShopSrvImpl();
				try {
					shopsrv.getMyGoods(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_ADD_ALL_GOODS)) {

				ShopSrv shopsrv = new ShopSrvImpl();
				try {
					shopsrv.addshoppingcart(rcvmsg, client);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_BUY_ALL_GOODS)) {

				ShopSrv shopsrv = new ShopSrvImpl();
				try {
					shopsrv.delMygood(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_COURSE)) {

				CourseSrv coursesrv = new CourseSrvImpl();
				try {
					coursesrv.getAllCourse(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_ADD_ALL_COURSE)) {

				CourseSrv coursesrv = new CourseSrvImpl();
				try {
					coursesrv.addCourse(rcvmsg, client);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_DELETE_ALL_COURSE)) {

				CourseSrv coursesrv = new CourseSrvImpl();
				try {
					coursesrv.delMyCourse(rcvmsg, client);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_MYCOURSE)) {

				CourseSrv coursesrv = new CourseSrvImpl();
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

				StuSrv stusrv = new StuSrvImpl();
				try {
					stusrv.getInfo(rcvmsg, client);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_UPDATE_MY_USER)) {

				StuSrv stusrv = new StuSrvImpl();
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

	/* (non-Javadoc)
	 * @see VC.server.bz.ServerThreadSrv#getClient()
	 */
	@Override
	public Socket getClient() {
		return client;
	}

	/* (non-Javadoc)
	 * @see VC.server.bz.ServerThreadSrv#setClient(java.net.Socket)
	 */
	@Override
	public void setClient(Socket client) {
		this.client = client;
	}

	/* (non-Javadoc)
	 * @see VC.server.bz.ServerThreadSrv#getUser()
	 */
	@Override
	public String getUser() {
		return User;
	}

	/* (non-Javadoc)
	 * @see VC.server.bz.ServerThreadSrv#setUser(java.lang.String)
	 */
	@Override
	public void setUser(String user) {
		User = user;
	}

	/* (non-Javadoc)
	 * @see VC.server.bz.ServerThreadSrv#isClosed()
	 */
	@Override
	public boolean isClosed() {
		return isClosed;
	}

	/* (non-Javadoc)
	 * @see VC.server.bz.ServerThreadSrv#setClosed(boolean)
	 */
	@Override
	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

}
