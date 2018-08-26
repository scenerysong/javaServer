package VC.server.bz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.common.BookMessage;
import VC.common.Message;
import VC.common.MessageType;
import VC.common.SocketConstant;
import VC.server.dao.LibraryDAO;

public class LibrarySrvImpl{

	public LibrarySrvImpl() {
		
	}

	
	public void searchByBooknameSend(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
		
		LibraryDAO librarydao = new LibraryDAO();
		BookMessage bookmsg = new BookMessage();
		bookmsg = (BookMessage) rcvmsg;
		List<Book> booklist = new ArrayList<Book>();
		BookMessage sendmsg = new BookMessage();
		
		//test
		//System.out.println(bookmsg.getBookname());
		
		booklist = librarydao.getBookByBookname(bookmsg.getBookname());
		
		//test
		//System.out.println(booklist.get(0).toString());
		
		sendmsg.setBookdata(booklist);
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
		
	}
}
