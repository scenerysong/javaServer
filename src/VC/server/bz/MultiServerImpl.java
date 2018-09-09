package VC.server.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import VC.common.Message;
import VC.common.MessageType;
import VC.common.SocketConstant;
import VC.server.vo.LoginSrv;
import VC.server.vo.MultiServer;
import VC.server.vo.ServerSrv;

public class MultiServerImpl implements ServerSrv, MultiServer {

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

	/* (non-Javadoc)
	 * @see VC.server.bz.MultiServer#run()
	 */
	@Override
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
				LoginSrv loginsrv = new LoginSrvImpl();
				
				try {
					flag = loginsrv.judgeLogin(rcvmsg, rsvsocket);
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
					loginsrv.addUser(rcvmsg, rsvsocket);
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
					loginsrv.delUser(rcvmsg, rsvsocket);
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

	/* (non-Javadoc)
	 * @see VC.server.bz.MultiServer#getServersocket()
	 */
	@Override
	public ServerSocket getServersocket() {
		return serversocket;
	}

	/* (non-Javadoc)
	 * @see VC.server.bz.MultiServer#setServersocket(java.net.ServerSocket)
	 */
	@Override
	public void setServersocket(ServerSocket serversocket) {
		this.serversocket = serversocket;
	}

	/* (non-Javadoc)
	 * @see VC.server.bz.MultiServer#isClosed()
	 */
	@Override
	public boolean isClosed() {
		return closed;
	}

	/* (non-Javadoc)
	 * @see VC.server.bz.MultiServer#setClosed(boolean)
	 */
	@Override
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
}
