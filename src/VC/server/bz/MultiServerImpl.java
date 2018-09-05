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

public class MultiServerImpl implements ServerSrv {

	private ServerSocket serversocket = null;
	private static final int SERVER_PORT = SocketConstant.SERVER_PORT;
	private boolean closed = false;

	public MultiServerImpl() {
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
			
			if (rcvmsg.getType().equals(MessageType.CMD_JUDGE_LOGIN)) {

				boolean flag = false;
				LoginSrvImpl loginsrv = new LoginSrvImpl();
				
				try {
					flag = loginsrv.judgeLogin(rcvmsg, rsvsocket);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//start a new thread
					// closed = true;
			}
			
			ServerThread st = new ServerThread(rsvsocket, rcvmsg.getID());
			new Thread(st).start();
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
