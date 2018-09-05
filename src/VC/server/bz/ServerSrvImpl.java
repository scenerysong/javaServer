package VC.server.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import VC.common.Message;
import VC.common.MessageType;
import VC.common.SocketConstant;
import VC.server.vo.ServerSrv;

public class ServerSrvImpl implements ServerSrv {

	private ServerSocket serversocket = null;
	private static final int SERVER_PORT = SocketConstant.SERVER_PORT;
	private boolean closed = false;

	public ServerSrvImpl() {
		try {
			this.setServersocket(new ServerSocket(SERVER_PORT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server is on the PORT " + SERVER_PORT + " listening");
	}

	public void run() {

		int i = 0;
		
		while (!isClosed()) {
			i++;
			System.out.println(i);
			Socket rsvsocket = null;
			try {
				rsvsocket = serversocket.accept();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println(rsvsocket.getPort());
			Message rcvmsg = new Message();
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(rsvsocket.getInputStream());
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
			System.out.println(rcvmsg.getType());
			
			if (rcvmsg.getType().equals(MessageType.CMD_QUY_BOOK_BOOKNAME)) {

				LibrarySrvImpl librarysrvimpl = new LibrarySrvImpl();
				try {
					librarysrvimpl.searchByBooknameSend(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_JUDGE_LOGIN)) {

				LoginSrvImpl loginsrv = new LoginSrvImpl();
				try {
					loginsrv.judgeLogin(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_COURSE)) {

				CourseSrvImpl coursesrv = new CourseSrvImpl();
				try {
					coursesrv.getAllCourse(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_ADD_ALL_COURSE)) {

				CourseSrvImpl coursesrv = new CourseSrvImpl();
				try {
					coursesrv.addCourse(rcvmsg, rsvsocket);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_DELETE_ALL_COURSE)) {

				CourseSrvImpl coursesrv = new CourseSrvImpl();
				try {
					coursesrv.delMyCourse(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_MYCOURSE)) {

				CourseSrvImpl coursesrv = new CourseSrvImpl();
				try {
					coursesrv.getMyCourse(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_BOOK)) {

				LibrarySrvImpl librarysrv = new LibrarySrvImpl();
				try {
					librarysrv.getAllBook(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_ADD_ALL_BOOK)) {

				LibrarySrvImpl librarysrv = new LibrarySrvImpl();
				try {
					librarysrv.borrowbook(rcvmsg, rsvsocket);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_DELETE_ALL_BOOK)) {

				LibrarySrvImpl librarysrv = new LibrarySrvImpl();
				try {
					librarysrv.returnbook(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_MYBOOK)) {

				LibrarySrvImpl librarysrv = new LibrarySrvImpl();
				try {
					librarysrv.getMyBook(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_GOODS)) {

				ShopSrvImpl shopsrv = new ShopSrvImpl();
				try {
					shopsrv.getAllGoods(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_GET_ALL_MYGOODS)) {

				ShopSrvImpl shopsrv = new ShopSrvImpl();
				try {
					shopsrv.getMyGoods(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_ADD_ALL_GOODS)) {

				ShopSrvImpl shopsrv = new ShopSrvImpl();
				try {
					shopsrv.addshoppingcart(rcvmsg, rsvsocket);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
			if (rcvmsg.getType().equals(MessageType.CMD_BUY_ALL_GOODS)) {

				ShopSrvImpl shopsrv = new ShopSrvImpl();
				try {
					shopsrv.delMygood(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// closed = true;
			}
		}
	}

	public ServerSocket getServersocket() {
		return serversocket;
	}

	public void setServersocket(ServerSocket serversocket) {
		this.serversocket = serversocket;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}
}
