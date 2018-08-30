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

public class ServerSrvImpl implements ServerSrv{

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

	//使用继承相关的类型转换方法,只需要添加MessageType的类型判断执行操作即可
	public void run() throws IOException, ClassNotFoundException, SQLException {

		while (!isClosed()) {
			Socket rsvsocket = serversocket.accept();

			Message rcvmsg = new Message();
			ObjectInputStream ois = new ObjectInputStream(rsvsocket.getInputStream());
			rcvmsg = (Message) ois.readObject();

			//对不同的MessageType进行判断,各个模块自行添加
			if (rcvmsg.getType().equals(MessageType.CMD_QUY_BOOK_BOOKNAME)) {
				
				LibrarySrvImpl librarysrvimpl = new LibrarySrvImpl();
				librarysrvimpl.searchByBooknameSend(rcvmsg, rsvsocket);
				
				closed = true;
			}
			if(rcvmsg.getType().equals(MessageType.CMD_JUDGE_LOGIN)){
				
				LoginSrvImpl loginsrv = new LoginSrvImpl();
				loginsrv.judgeLogin(rcvmsg, rsvsocket);
				
				closed = true;
			}
			if(rcvmsg.getType().equals(MessageType.CMD_GET_ALL_GOODS)){
				
				ShopSrvImpl shopsrv = new ShopSrvImpl();
				shopsrv.getAllGoods(rcvmsg, rsvsocket);
				
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
