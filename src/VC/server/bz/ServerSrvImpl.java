package VC.server.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Message;
import VC.common.MessageType;
import VC.common.SocketConstant;
import VC.server.dao.LibraryDAO;
import VC.common.Book;
import VC.common.BookMessage;

public class ServerSrvImpl {

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

	public void run() throws IOException, ClassNotFoundException, SQLException {

		while (!isClosed()) {
			Socket rsvsocket = serversocket.accept();

			Message rcvmsg = new Message();
			ObjectInputStream ois = new ObjectInputStream(rsvsocket.getInputStream());
			rcvmsg = (Message) ois.readObject();

			//test
			//System.out.println(rcvmsg.getSender());
			//System.out.println(rcvmsg.getType());
			
			if (rcvmsg.getType().equals(MessageType.CMD_QUY_BOOK_BOOKNAME)) {
				
				//test
				//System.out.println("enter");
				
				LibrarySrvImpl librarysrvimpl = new LibrarySrvImpl();
				librarysrvimpl.searchByBooknameSend(rcvmsg, rsvsocket);
				
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
