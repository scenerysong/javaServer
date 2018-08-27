package VC.server.bz;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.common.BookMessage;
import VC.common.Message;
import VC.server.dao.LibraryDAO;
import VC.server.vo.LibrarySrv;

public class LibrarySrvImpl implements LibrarySrv{

	public LibrarySrvImpl() {
		
	}
	
	//注意此处传入的参数
	public void searchByBooknameSend(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
		
		//使用的是类型转换方法实现父类到子类的转换
		LibraryDAO librarydao = new LibraryDAO();
		BookMessage bookmsg = new BookMessage();
		bookmsg = (BookMessage) rcvmsg;
		List<Book> booklist = new ArrayList<Book>();
		BookMessage sendmsg = new BookMessage();
		
		//调用dao里的方法
		booklist = librarydao.getBookByBookname(bookmsg.getBookname());
		
		sendmsg.setBookdata(booklist);
		
		//发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
		
	}
}
