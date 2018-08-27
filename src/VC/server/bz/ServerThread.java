package VC.server.bz;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;
import VC.common.MessageType;
import VC.common.SocketConstant;
	
public class ServerThread implements Runnable {
	
	private ServerSocket serversocket = null;
	private static final int SERVER_PORT = SocketConstant.SERVER_PORT;
	private boolean closed = false;

	public ServerThread() {
			try {
				this.setServersocket(new ServerSocket(SERVER_PORT));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Server is on the PORT " + SERVER_PORT + " listening");
		}

	// 浣跨敤缁ф壙鐩稿叧鐨勭被鍨嬭浆鎹㈡柟娉�,鍙渶瑕佹坊鍔燤essageType鐨勭被鍨嬪垽鏂墽琛屾搷浣滃嵆鍙�
	public void run() {

		while (!isClosed()) {
			Socket rsvsocket = null;
			try {
				rsvsocket = serversocket.accept();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			Message rcvmsg = new Message();
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(rsvsocket.getInputStream());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				rcvmsg = (Message) ois.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// 瀵逛笉鍚岀殑MessageType杩涜鍒ゆ柇,鍚勪釜妯″潡鑷娣诲姞
			if (rcvmsg.getType().equals(MessageType.CMD_QUY_BOOK_BOOKNAME)) {

				LibrarySrvImpl librarysrvimpl = new LibrarySrvImpl();
				try {
					librarysrvimpl.searchByBooknameSend(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				closed = true;
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

