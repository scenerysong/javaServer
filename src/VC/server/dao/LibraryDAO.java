package VC.server.dao;

/**
 * class {@code LibraryDAO} is the subclass of class {@link VC.server.db.DBstart} for database.
 * <p>it is used to get the information from database according to the requirements and do some simple data processing if needed.
 * including methods for books.
 * <p>
 * @author Guangwei Xiong
 * @author Linsong Wang 
 * 
 * @version 1.0
*/

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.common.Course;
import VC.server.db.DBstart;

public class LibraryDAO extends DBstart {

	public LibraryDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	/**
	 * 
	 * search the book in database through a given name 
	 *
	 *<pre>{@code
	 * show how to use this method
	 * 
	 * String bookname = "Introduction to Algorithms";
	 * LibraryDAO librarydao = new libraryDAO();
	 * 
	 * List<book> books = new ArrayList<Book>();
	 * books = librarydao.getBookByBookname(bookname)
	 * }</pre>
	 * 
	 * @param bookname
	 * @return the information of the book
	 * @throws SQLException
	 */
	public List<Book> getBookByBookname(String bookname) throws SQLException {

		paras = new String[1];
		paras[0] = null;
		paras[0] = bookname;
		sql = "select * from books where bookname = ?";
		ps = ct.prepareStatement(sql);
		for (int i = 0; i < paras.length; i++) {
			ps.setString(i + 1, paras[i]);
		}
		rs = ps.executeQuery();

		Book book = new Book();
		List<Book> booklist = new ArrayList<Book>();

		while (rs.next()) {

			book = new Book();
			book.setBookName(rs.getString("bookname"));
			book.setBookAuthor(rs.getString("bookauthor"));
			book.setBookPublisher(rs.getString("bookpublisher"));

			booklist.add(book);
		}
		return booklist;
	}

	/**
	 * 
	 * borrow the book for the User
	 * 
	 * <pre>{@code
	 * show how to use this method
	 * 
	 * String bookname = "Introduction to Algorithms";
	 * String User = "wls";
	 * LibraryDAO librarydao = new libraryDAO();
	 * 
	 * flag = librarydao.BorrowBook(User, bookname);
	 * }
	 * </pre>
	 * @param Users
	 * @param BookName
	 * @return the result of the operation
	 * @throws SQLException
	 */
	public boolean BorrowBook(String Users, String BookName) throws SQLException {
		sql = "select * from books where bookname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, BookName);
		rs = ps.executeQuery();

		/* ；课程名称默认不重复 */
		Book book = new Book();
		if (rs.next()) {
			// book.setBookID(rs.getString("ID"));
			book.setBookName(rs.getString("bookname"));
			book.setBookAuthor(rs.getString("bookauthor"));
			book.setBookPublisher(rs.getString("bookpublisher"));
			sql = "insert into BookUser (bookname, bookpublisher, bookauthor, User) values (?, ?, ?, ?)";
			ps = ct.prepareStatement(sql);
			// ps.setString(1, book.getBookID());
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getBookPublisher());
			ps.setString(3, book.getBookAuthor());
			ps.setString(4, Users);
			if (ps.executeUpdate() > 0)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	/**
	 * 
	 * return all the books the user has borrowed
	 * 
	 * <pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * LibraryDAO librarydao = new libraryDAO();
	 * 
	 * List<book> books = new ArrayList<Book>();
	 * books = librarydao.GetAllMyBook(User);
	 * }</pre>
	 * 
	 * 
	 * @param Users
	 * @return the information of the book user has borrowed
	 * @throws SQLException
	 */
	public List<Book> GetAllMyBook(String Users) throws SQLException {
		sql = "select * from BookUser where User = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		rs = ps.executeQuery();

		Book bk = new Book();
		List<Book> Bk = new ArrayList<Book>();

		while (rs.next()) {
			bk = new Book();

			// bk.setBookID(rs.getString("ID"));
			bk.setBookName(rs.getString("bookname"));
			bk.setBookPublisher(rs.getString("bookpublisher"));
			bk.setBookAuthor("bookauthor");

			Bk.add(bk);
		}
		return Bk;
	}

	/**
	 * 
	 * get all of the books in the database
	 * 
	 * <pre>{@code
	 * show how to use this method
	 * 
	 * LibraryDAO librarydao = new libraryDAO();
	 * 
	 * List<book> books = new ArrayList<Book>();
	 * books = librarydao.getAllBook();
	 * }</pre>
	 * 
	 * @return all books in database
	 * @throws SQLException
	 */
	
	public List<Book> getAllBook() throws SQLException {

		sql = "select * from books";
		ps = ct.prepareStatement(sql);
		rs = ps.executeQuery();

		Book bk = new Book();
		List<Book> bks = new ArrayList<Book>();

		while (rs.next()) {

			bk = new Book();
			bk.setBookName(rs.getString("bookname"));
			bk.setBookAuthor(rs.getString("bookauthor"));
			bk.setBookPublisher(rs.getString("bookpublisher"));

			bks.add(bk);
		}

		return bks;
	}

	/**
	 * 
	 * delete the record of borrowed book
	 * 
	 * <pre>{@code
	 * show how to use this method
	 * 
	 * String User = "wls";
	 * String bookname = "ABC";
	 * LibraryDAO librarydao = new libraryDAO();
	 * 
	 * flag = library.returnBook(User, bookname);
	 * }</pre>
	 *
	 * @param Users
	 * @param bookname
	 * @return the result of operation
	 * @throws SQLException
	 */
	
	public boolean returnbook(String Users, String bookname) throws SQLException {
		sql = "delete from BookUser where User = ? and bookname = ?";
		ps = ct.prepareStatement(sql);
		ps.setString(1, Users);
		ps.setString(2, bookname);
		if (ps.executeUpdate() > 0)
			return true;
		else
			return false;
	}
}
