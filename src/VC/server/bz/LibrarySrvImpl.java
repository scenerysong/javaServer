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

public class LibrarySrvImpl implements LibrarySrv {

	public LibraryDAO librarydao;

	public LibrarySrvImpl() {

		try {
			librarydao = new LibraryDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 注意此处传入的参数
	public void searchByBooknameSend(Message rcvmsg, Socket socket)
			throws SQLException, IOException, ClassNotFoundException {

		// 使用的是类型转换方法实现父类到子类的转换
		BookMessage bookmsg = new BookMessage();
		bookmsg = (BookMessage) rcvmsg;
		List<Book> booklist = new ArrayList<Book>();
		BookMessage sendmsg = new BookMessage();

		// 调用dao里的方法
		booklist = librarydao.getBookByBookname(bookmsg.getBookname());

		sendmsg.setBooklist(booklist);

		// 发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();

	}

	public void getAllBook(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {

		List<Book> Booklist = new ArrayList<Book>();
		BookMessage sendmsg = new BookMessage();

		// 调用dao里的方法
		Booklist = librarydao.getAllBook();

		sendmsg.setBooklist(Booklist);

		// 发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}

	public void getMyBook(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {

		List<Book> Booklist = new ArrayList<Book>();
		BookMessage sendmsg = new BookMessage();
		String username = rcvmsg.getID();

		// 调用dao里的方法
		Booklist = librarydao.GetAllMyBook(username);

		sendmsg.setBooklist(Booklist);

		// 发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	public void borrowbook(Message rcvmsg, Socket socket) throws SQLException, IOException {

		BookMessage sendmsg = new BookMessage();
		String bookname = null;
		String username = null;
		boolean res = false;
		BookMessage rmsg = (BookMessage) rcvmsg;
		bookname = rmsg.getBookname();
		username = rmsg.getID();
		
		res = librarydao.BorrowBook(username, bookname);

		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	public void returnbook(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
		
		BookMessage sendmsg = new BookMessage();
		String Bookname = null;
		String username = null;
		boolean res = false;
		BookMessage rmsg = (BookMessage) rcvmsg;
		Bookname = rmsg.getBookname();
		username = rmsg.getID();
		
		System.out.println("kai shi tui ke step2");
		res = librarydao.returnbook(username, Bookname);

		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
}
